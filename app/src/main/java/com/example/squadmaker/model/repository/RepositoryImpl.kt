package com.example.squadmaker.model.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.squadmaker.model.database.dao.CharactersDao
import com.example.squadmaker.model.database.dao.ComicsDao
import com.example.squadmaker.model.database.dao.DetailedCharacterDao
import com.example.squadmaker.model.database.dao.SquadDao
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.ComicsEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity
import com.example.squadmaker.model.network.api.MarvelApiService
import com.example.squadmaker.model.network.comicsresponse.ComicsDetails
import com.example.squadmaker.model.network.comicsresponse.Data
import com.example.squadmaker.model.network.response.Character
import com.example.squadmaker.utils.Constants
import retrofit2.HttpException
import java.math.BigInteger
import java.security.MessageDigest
import com.example.squadmaker.model.network.comicsresponse.Response as ComicsResponse


class RepositoryImpl(
    private val charactersDao: CharactersDao,
    private val squadDao: SquadDao,
    private val detailedCharacterDao: DetailedCharacterDao,
    private val comicsDao: ComicsDao,
    private val marvelApiService: MarvelApiService
) : Repository {

    // region Fields

    private val TAG = RepositoryImpl::class.java.simpleName

    // endregion

    // region Public Functions

    override suspend fun fetchAndSaveCharacters() {
        val fetchedCharactersList = fetchCharacters()
        val characterEntityList = getCharacterEntityList(fetchedCharactersList)

        charactersDao.deleteAndInsertCharacters(characterEntityList)
    }

    override suspend fun fetchAndSaveDetailedCharacterById(id: Int) {
        val character = fetchDetailedCharacterById(id)
        val isInSquad = squadDao.isCharacterInSquad(id).isNotEmpty()
        val characterEntity = getDetailedCharacterEntity(character, isInSquad)

        detailedCharacterDao.replaceDetailedCharacter(characterEntity)
    }

    override suspend fun removeDetailedCharacter() {
        detailedCharacterDao.deleteDetailedCharacters()
        comicsDao.deleteComics()
    }

    override suspend fun fetchAndSaveComicsByCharacterId(id: Int) {
        val comicsResponse = fetchComicsByCharacterId(id)
        val comicsEntities = getComicEntities(comicsResponse)

        comicsDao.replaceComics(comicsEntities)
    }

    override suspend fun updateSquadEntry(isSquadMember: Boolean) {
        val currentDetailedCharacter = detailedCharacterDao.getDetailedCharacterEntity()
        if (isSquadMember) {
            squadDao.deleteSquadMember(currentDetailedCharacter.id)
        } else {
            val squadEntity = getSquadEntity(currentDetailedCharacter)
            squadDao.insertSquadMember(squadEntity)
        }
    }

    // endregion

    // region Private Functions

    private fun getSquadEntity(detailedCharacter: DetailedCharacterEntity): SquadEntity {
        return SquadEntity(detailedCharacter.id, detailedCharacter.resourceUrl)
    }

    private suspend fun fetchCharacters(): List<Character> {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        return try {
            val response = marvelApiService.getCharacters(ts = ts, hash = md5)
            response.data.results
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            listOf()
        }
    }

    private suspend fun fetchDetailedCharacterById(id: Int): Character {
        val md5Input = getMd5Input()
        val md5 = md5Input.md5()
        val ts = getCurrentTimestamp()

        val response = marvelApiService.getCharacterById(id.toString(), ts = ts, hash = md5)
        return response.data.results[0]
    }

    private suspend fun fetchComicsByCharacterId(id: Int): ComicsResponse {
        val md5Input = getMd5Input()
        val md5 = md5Input.md5()
        val ts = getCurrentTimestamp()

        return try {
            marvelApiService.getComicsByCharacterId(
                characterId = id.toString(),
                ts = ts,
                hash = md5
            )
        } catch (e: HttpException) {
            Log.e(TAG, e.message())
            ComicsResponse(
                code = "",
                status = "",
                data = Data(limit = "", total = "", results = listOf())
            )
        }
    }

    private fun getDetailedCharacterEntity(
        character: Character,
        isInSquad: Boolean
    ): DetailedCharacterEntity {
        val thumbnailPath =
            getThumbnailStringUri(character.thumbnail.path, character.thumbnail.extension)

        return DetailedCharacterEntity(
            character.id.toInt(),
            character.name,
            character.description,
            thumbnailPath,
            character.comics.available.toInt(),
            isInSquad
        )
    }

    private fun getCharacterEntityList(fetchedCharacterList: List<Character>): List<CharacterEntity> {
        val characterEntityList = mutableListOf<CharacterEntity>()

        for (fetchedCharacter in fetchedCharacterList) {
            val characterEntity = getCharacterEntity(fetchedCharacter)
            characterEntityList.add(characterEntity)
        }

        return characterEntityList
    }

    private fun getCharacterEntity(fetchedCharacter: Character): CharacterEntity {
        val id = fetchedCharacter.id.toInt()
        val name = fetchedCharacter.name
        val thumbnailStringUri = getThumbnailStringUri(
            fetchedCharacter.thumbnail.path,
            fetchedCharacter.thumbnail.extension
        )

        return CharacterEntity(id, name, thumbnailStringUri)
    }

    private fun getComicEntities(response: ComicsResponse): List<ComicsEntity> {
        val comics: List<ComicsDetails> = response.data.results
        if (comics.isNullOrEmpty()) {
            return listOf()
        }
        val availableComics = response.data.total
        val comicEntities = mutableListOf<ComicsEntity>()
        for ((entriesInList, comic) in comics.withIndex()) {
            if (entriesInList > 1) {
                return comicEntities
            }
            val resourcePath =
                getThumbnailStringUri(comic.thumbnail.path, comic.thumbnail.extension)
            comicEntities.add(
                ComicsEntity(
                    entriesInList,
                    availableComics.toInt(),
                    resourcePath,
                    comic.title
                )
            )
        }
        return comicEntities
    }

    private fun getThumbnailStringUri(thumbnailPath: String, thumbnailExtension: String): String {
        return Uri.parse(
            thumbnailPath
                .plus(".")
                .plus(thumbnailExtension)
        ).toString()
    }

    private fun getMd5Hash(): String {
        val md5input = getMd5Input()
        return md5input.md5()
    }

    private fun getMd5Input(): String {
        return (getCurrentTimestamp())
            .plus(Constants.PRIVATE_API_KEY)
            .plus(Constants.PUBLIC_API_KEY)
    }

    private fun getCurrentTimestamp(): String {
        return (System.currentTimeMillis() / 1000L).toString()
    }

    // endregion

    // region LiveData observing

    fun getCharacters(): LiveData<List<CharacterEntity>> {
        return charactersDao.getAllCharacters()
    }

    fun getSquad(): LiveData<List<SquadEntity>> {
        return squadDao.getSquad()
    }

    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity> {
        return detailedCharacterDao.getDetailedCharacter()
    }

    fun getComics(): LiveData<List<ComicsEntity>> {
        return comicsDao.getComics()
    }

    // endregion

    // region Extension Functions

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    // endregion
}

