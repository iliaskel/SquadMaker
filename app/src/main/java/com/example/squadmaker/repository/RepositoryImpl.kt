package com.example.squadmaker.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.LocalDataSource
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsDetailsDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsResponseDTO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repository {

    // region Fields


    // endregion

    // region Public Functions

    override suspend fun fetchAndSaveCharacters() {
        val fetchedCharactersList = remoteDataSource.fetchCharacters()
        val characterEntityList = getCharacterEntityList(fetchedCharactersList)

        localDataSource.replaceCharacterList(characterEntityList)
    }

    override suspend fun fetchAndSaveDetailedCharacterById(characterId: Int) {
        val character = remoteDataSource.fetchDetailedCharacterById(characterId)
        val isInSquad = localDataSource.getSquadListForCharacterId(characterId).isNotEmpty()
        val characterEntity = getDetailedCharacterEntity(character, isInSquad)

        localDataSource.replaceDetailedCharacter(characterEntity)
    }

    override suspend fun removeDetailedCharacter() {
        localDataSource.deleteDetailedCharacter()
        localDataSource.deleteComics()
    }

    override suspend fun fetchAndSaveComicsByCharacterId(characterId: Int) {
        val comicsResponse = remoteDataSource.getComicsForCharacterId(characterId)
        val comicsEntities = getComicEntities(comicsResponse)

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

    private fun getDetailedCharacterEntity(
        characterDTO: CharacterDTO,
        isInSquad: Boolean
    ): DetailedCharacterEntity {
        val thumbnailPath =
            getThumbnailStringUri(
                characterDTO.thumbnailDTO.path,
                characterDTO.thumbnailDTO.extension
            )

        return DetailedCharacterEntity(
            characterDTO.id.toInt(),
            characterDTO.name,
            characterDTO.description,
            thumbnailPath,
            characterDTO.comicsDTO.available.toInt(),
            isInSquad
        )
    }

    private fun getCharacterEntityList(fetchedCharacterDTOList: List<CharacterDTO>): List<CharacterEntity> {
        val characterEntityList = mutableListOf<CharacterEntity>()

        for (fetchedCharacter in fetchedCharacterDTOList) {
            val characterEntity = getCharacterEntity(fetchedCharacter)
            characterEntityList.add(characterEntity)
        }

        return characterEntityList
    }

    private fun getCharacterEntity(fetchedCharacterDTO: CharacterDTO): CharacterEntity {
        val id = fetchedCharacterDTO.id.toInt()
        val name = fetchedCharacterDTO.name
        val thumbnailStringUri = getThumbnailStringUri(
            fetchedCharacterDTO.thumbnailDTO.path,
            fetchedCharacterDTO.thumbnailDTO.extension
        )
        return CharacterEntity(id, name, thumbnailStringUri)
    }

    private fun getComicEntities(responseDTO: ComicsResponseDTO): List<ComicsEntity> {
        val comics: List<ComicsDetailsDTO> = responseDTO.dataDTO.results
        if (comics.isNullOrEmpty()) {
            return listOf()
        }

        val availableComics = responseDTO.dataDTO.total
        val comicEntities = mutableListOf<ComicsEntity>()
        for ((entriesInList, comic) in comics.withIndex()) {
            val resourcePath =
                getThumbnailStringUri(comic.thumbnailDTO.path, comic.thumbnailDTO.extension)
            comicEntities.add(
                ComicsEntity(
                    entriesInList,
                    availableComics.toInt(),
                    resourcePath,
                    comic.title
                )
            )
        }
        return comicEntities
    }

    private fun getThumbnailStringUri(thumbnailPath: String, thumbnailExtension: String): String {
        return Uri.parse(
            thumbnailPath
                .plus(".")
                .plus(thumbnailExtension)
        ).toString()
    }

    // endregion

    // region LiveData observing

    override fun getCharacters(): Flow<List<CharacterEntity>> {
        return localDataSource.getAllCharacters()
    }

    override fun getSquad(): LiveData<List<SquadEntity>> {
        return localDataSource.getSquad()
    }

    override fun getDetailedCharacter(): LiveData<DetailedCharacterEntity> {
        return localDataSource.getDetailedCharacter()
    }

    override fun getComics(): LiveData<List<ComicsEntity>> {
        return localDataSource.getComics()
    }

    // endregion
}

