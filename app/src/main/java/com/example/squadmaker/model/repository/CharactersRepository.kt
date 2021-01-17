package com.example.squadmaker.model.repository

import com.example.squadmaker.model.localdatasouce.roomdatabase.datasource.CharactersLocalDataSource
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val charactersMapper: DTOToEntityMapper<CharacterResultsDTO, CharacterEntity>
) : CharactersRepository {

    override suspend fun fetchAndSaveCharacters() {
        val characters: List<CharacterResultsDTO> = remoteDataSource.fetchCharacters()
        val mappedCharacters: List<CharacterEntity> =
            charactersMapper.mapDTOToEntityList(characters)

        charactersLocalDataSource.replaceCharacterList(mappedCharacters)
    }

    override fun getCharacterByCharacterId(characterId: Int): Flow<CharacterEntity?> {
        return charactersLocalDataSource.getCharacterByCharacterId(characterId)
    }

    override fun getCharactersListFlow(): Flow<List<CharacterEntity>>? {
        return charactersLocalDataSource.getAllCharacters()
    }
}

interface CharactersRepository {

    suspend fun fetchAndSaveCharacters()

    fun getCharacterByCharacterId(characterId: Int): Flow<CharacterEntity?>

    fun getCharactersListFlow(): Flow<List<CharacterEntity>>?
}