package com.example.squadmaker.model.repository

import com.example.squadmaker.model.localdatasouce.LocalDataSource
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.repository.mapper.Mapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: Mapper
) : Repository {

    // region Public Functions

    override suspend fun fetchAndSaveCharacters() {
        val fetchedCharactersList = remoteDataSource.fetchCharacters()
        val characterEntityList = mapper.mapToCharacterEntityList(fetchedCharactersList)

        localDataSource.replaceCharacterList(characterEntityList)
    }

    override suspend fun fetchAndSaveDetailedCharacterById(characterId: Int) {
        val character = remoteDataSource.fetchDetailedCharacterById(characterId)
        val isInSquad = localDataSource.getSquadListForCharacterId(characterId).isNotEmpty()
        val characterEntity = mapper.mapToDetailedCharacter(character)
        characterEntity.isSquadMember = isInSquad

        localDataSource.replaceDetailedCharacter(characterEntity)
    }

    override suspend fun removeDetailedCharacter() {
        localDataSource.deleteDetailedCharacter()
        localDataSource.deleteComics()
    }

    override suspend fun fetchAndSaveComicsByCharacterId(characterId: Int) {
        val comicsResponse = remoteDataSource.getComicsForCharacterId(characterId)
        val comicsEntities = mapper.mapToComicEntity(comicsResponse)

        localDataSource.replaceComicsList(comicsEntities)
    }

    override suspend fun updateSquadEntry(isSquadMember: Boolean) {
        val currentDetailedCharacter = localDataSource.getDetailedCharacterEntity()
        if (isSquadMember) {
            localDataSource.deleteSquadMember(currentDetailedCharacter.id)
        } else {
            val squadEntity = getSquadEntity(currentDetailedCharacter)
            localDataSource.insertSquadMember(squadEntity)
        }
    }

    // endregion

    // region Private Functions

    private fun getSquadEntity(detailedCharacter: DetailedCharacterEntity): SquadEntity {
        return SquadEntity(detailedCharacter.id, detailedCharacter.resourceUrl)
    }

    // endregion

    // region LiveData observing

    override fun getCharacters(): Flow<List<CharacterEntity>> {
        return localDataSource.getAllCharacters()
    }

    override fun getSquad(): Flow<List<SquadEntity>> {
        return localDataSource.getSquad()
    }

    override fun getDetailedCharacter(): Flow<DetailedCharacterEntity?> {
        return localDataSource.getDetailedCharacter()
    }

    override fun getComics(): Flow<List<ComicsEntity>> {
        return localDataSource.getComics()
    }

    // endregion
}

