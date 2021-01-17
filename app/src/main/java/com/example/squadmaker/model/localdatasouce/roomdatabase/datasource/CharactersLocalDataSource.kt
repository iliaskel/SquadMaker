package com.example.squadmaker.model.localdatasouce.roomdatabase.datasource

import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersLocalDataSourceImpl @Inject constructor(squadDatabase: SquadDatabase) :
    CharactersLocalDataSource {

    private val charactersDao = squadDatabase.charactersDao()

    override suspend fun replaceCharacterList(characterEntityList: List<CharacterEntity>) {
        charactersDao.replaceCharacterList(characterEntityList)
    }

    override fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return charactersDao.getAllCharactersListFlow()
    }

    override fun getCharacterByCharacterId(characterId: Int): Flow<CharacterEntity?> {
        return charactersDao.getCharacterByCharacterIdFlow(characterId)
    }

}

interface CharactersLocalDataSource {

    suspend fun replaceCharacterList(characterEntityList: List<CharacterEntity>)

    fun getAllCharacters(): Flow<List<CharacterEntity>>

    fun getCharacterByCharacterId(characterId: Int): Flow<CharacterEntity?>

}