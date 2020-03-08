package com.example.squadmaker.model.repository

interface Repository {
    suspend fun fetchAndSaveCharacters()
    suspend fun fetchAndSaveDetailedCharacterById(id: Int)
    suspend fun removeDetailedCharacter()
    suspend fun fetchAndSaveComicsByCharacterId(id: Int)
    suspend fun updateSquadEntry(isSquadMember: Boolean)
}