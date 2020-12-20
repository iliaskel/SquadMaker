package com.example.squadmaker.model.localdatasouce

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharactersLocalDataSource {

    suspend fun replaceCharacterList(characterEntityList: List<CharacterEntity>)

    fun getAllCharacters(): Flow<List<CharacterEntity>>

}