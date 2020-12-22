package com.example.squadmaker.model.repository

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

    fun getDetailedCharacter(): Flow<DetailedCharacterEntity?>

    fun getComics(): Flow<List<ComicsEntity>>

    fun getCharacters(): Flow<List<CharacterEntity>>

    fun getSquad(): Flow<List<SquadEntity>>
}