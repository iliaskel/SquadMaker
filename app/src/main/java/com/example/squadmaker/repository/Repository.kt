package com.example.squadmaker.repository

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun fetchAndSaveCharacters()

    suspend fun fetchAndSaveDetailedCharacterById(characterId: Int)

    suspend fun removeDetailedCharacter()

    suspend fun fetchAndSaveComicsByCharacterId(characterId: Int)

    suspend fun updateSquadEntry(isSquadMember: Boolean)

    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity>

    fun getComics(): LiveData<List<ComicsEntity>>

    fun getCharacters(): Flow<List<CharacterEntity>>

    fun getSquad(): LiveData<List<SquadEntity>>
}