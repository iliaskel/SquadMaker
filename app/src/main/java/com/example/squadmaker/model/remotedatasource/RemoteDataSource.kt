package com.example.squadmaker.model.remotedatasource

import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsResponseDTO

interface RemoteDataSource {

    suspend fun fetchCharacters(): List<CharacterDTO>

    suspend fun fetchDetailedCharacterById(characterId: Int): CharacterDTO

    suspend fun getComicsForCharacterId(characterId: Int): ComicsResponseDTO
}