package com.example.squadmaker.model

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.squadmaker.model.database.SquadDatabase
import com.example.squadmaker.model.database.dao.CharactersDao
import com.example.squadmaker.model.database.dao.ComicsDao
import com.example.squadmaker.model.database.dao.DetailedCharacterDao
import com.example.squadmaker.model.database.dao.SquadDao
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.ComicsEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity
import com.example.squadmaker.model.network.api.MarvelApiService
import com.example.squadmaker.model.network.api.RetrofitBuilder
import com.example.squadmaker.model.network.comicsresponse.ComicsDetails
import com.example.squadmaker.model.network.response.Character
import com.example.squadmaker.utils.Constants
import java.math.BigInteger
import java.security.MessageDigest
import com.example.squadmaker.model.network.comicsresponse.Response as ComicsResponse


class Repository private constructor(application: Application) {

    private var marvelApiService: MarvelApiService
    private var charactersDao: CharactersDao
    private var detailedCharacterDao: DetailedCharacterDao
    private var squadDao: SquadDao
    private var comicsDao: ComicsDao

    init {
        val marvelDatabase = SquadDatabase.getInstance(application)
        charactersDao = marvelDatabase.charactersDao()
        squadDao = marvelDatabase.squadDao()
        detailedCharacterDao = marvelDatabase.detailedCharacterDao()
        comicsDao = marvelDatabase.comicsDao()
        marvelApiService = RetrofitBuilder.marvelApiService
    }

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(application: Application): Repository {
            if (instance != null) {
                return instance as Repository
            }
            synchronized(Repository) {
                val _instance = Repository(application)
                instance = _instance
                return _instance
            }
        }
    }

    // region Public Functions


    suspend fun fetchAndSaveCharacters() {
        val fetchedCharactersList = fetchCharacters()
        val characterEntityList = getCharacterEntityList(fetchedCharactersList)

        charactersDao.deleteAndInsertCharacters(characterEntityList)
    }

    suspend fun fetchAndSaveDetailedCharacterById(id: Int) {
        val character = fetchDetailedCharacterById(id)
        val isInSquad = squadDao.isCharacterInSquad(id).isNotEmpty()
        val characterEntity = getDetailedCharacterEntity(character, isInSquad)

        detailedCharacterDao.replaceDetailedCharacter(characterEntity)
    }

    suspend fun removeDetailedCharacter() {
        detailedCharacterDao.deleteDetailedCharacters()
        comicsDao.deleteComics()
    }

    suspend fun fetchAndSAveComicsByCharacterId(id: Int) {
        val comicsResponse = fetchComicsByCharacterId(id)
        val comicsEntities = getComicEntities(comicsResponse)

        comicsDao.replaceComics(comicsEntities)
    }

    suspend fun updateSquadEntry(isSquadMember: Boolean) {
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

        val response = marvelApiService.getCharacters(ts = ts, hash = md5)

        return response.data.results
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

        val response = marvelApiService.getComicsByCharacterId(
            characterId = id.toString(),
            ts = ts,
            hash = md5
        )
        return response
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

