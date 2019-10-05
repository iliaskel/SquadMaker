package com.example.squadmaker.model

import android.app.Application
import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.squadmaker.model.database.SquadDatabase
import com.example.squadmaker.model.database.dao.CharactersDao
import com.example.squadmaker.model.database.dao.DetailedCharacterDao
import com.example.squadmaker.model.database.dao.SquadDao
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity
import com.example.squadmaker.model.network.api.MarvelApiService
import com.example.squadmaker.model.network.api.RetrofitBuilder
import com.example.squadmaker.model.network.response.Character
import com.example.squadmaker.utils.Constants
import java.math.BigInteger
import java.security.MessageDigest


class Repository private constructor(application: Application) {

    private val TAG = "REPOSITORY :: "

    private var marvelApiService: MarvelApiService
    private var charactersDao: CharactersDao
    private var detailedCharacterDao: DetailedCharacterDao
    private var squadDao: SquadDao

    init {
        val marvelDatabase = SquadDatabase.getInstance(application)
        charactersDao = marvelDatabase.charactersDao()
        squadDao = marvelDatabase.squadDao()
        detailedCharacterDao = marvelDatabase.detailedCharacterDao()
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

    // region Public Functions for Main Fragment


    // endregion

    suspend fun fetchAndSaveCharacters() {
        val fetchedCharactersList = fetchCharacters()
        val characterEntityList = getCharacterEntityList(fetchedCharactersList)
        charactersDao.deleteAndInsertCharacters(characterEntityList)
    }

    suspend fun fetchAndSaveCharacterById(id: Int) {
        val character = fetchCharacterById(id)
        val isInSquad = squadDao.isUserInSquad(id).isNotEmpty()
        val characterEntity = getDetailedCharacterEntity(character, isInSquad)
        detailedCharacterDao.replaceDetailedCharacter(characterEntity)
    }

    // endregion

    // region Private Functions

    private suspend fun fetchCharacterById(id: Int): Character {
        val md5Input = getMd5Input()
        val md5 = md5Input.md5()
        val ts = getCurrentTimestamp()

        val response = marvelApiService.getCharacterById(id.toString(), ts = ts, hash = md5)
        return response.data.results[0]

    }

    private fun getDetailedCharacterEntity(
        character: Character,
        isInSquad: Boolean
    ): DetailedCharacterEntity {
        val thumbnailPath = getThumbnailStringUri(character)

        return DetailedCharacterEntity(
            character.id.toInt(),
            character.name,
            character.description,
            thumbnailPath,
            character.comics.available.toInt(),
            isInSquad
        )
    }

    private suspend fun fetchCharacters(): List<Character> {
        val md5 = getMd5Hash()
        val ts = getCurrentTimestamp()

        val response = marvelApiService.getCharacters(ts = ts, hash = md5)

        return response.data.results
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
        val thumbnailStringUri = getThumbnailStringUri(fetchedCharacter)

        return CharacterEntity(id, name, thumbnailStringUri)
    }

    private fun getThumbnailStringUri(fetchedCharacter: Character): String {
        return Uri.parse(
            fetchedCharacter.thumbnail.path
                .plus(".")
                .plus(fetchedCharacter.thumbnail.extension)
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

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    private fun getCurrentTimestamp(): String {
        return (System.currentTimeMillis() / 1000L).toString()
    }

    // endregion

    // region LiveData observing

    fun getCharacters(): LiveData<List<CharacterEntity>> {
        return charactersDao.getAllCharacters()
    }

    fun getMySquad(): LiveData<List<SquadEntity>> {
        return squadDao.getSquad()
    }

    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity> {
        return detailedCharacterDao.getDetailedCharacter()
    }

    suspend fun removeDetailedCharacter() {
        detailedCharacterDao.deleteDetailedCharacters()
    }

    // endregion
}

