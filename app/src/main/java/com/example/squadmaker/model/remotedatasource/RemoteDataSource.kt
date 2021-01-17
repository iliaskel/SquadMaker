package com.example.squadmaker.model.remotedatasource

import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO

interface RemoteDataSource {

    suspend fun fetchCharacters(): List<CharacterResultsDTO>

    suspend fun fetchDetailedCharacterById(characterId: Int): CharacterResultsDTO

    suspend fun fetchComicsForCharacterId(characterId: Int): ComicsResponseDTO
}