package com.example.squadmaker.model.repository.mapper.dtotoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsDetailsDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsResponseDTO
import com.example.squadmaker.model.repository.utils.concat
import javax.inject.Inject

class ComicsMapper
@Inject
constructor() : DTOToEntityMapper<ComicsResponseDTO, @JvmSuppressWildcards List<ComicsEntity>> {

    // region Implements Mapper

    override fun mapDTOToEntity(dtoObject: ComicsResponseDTO): List<ComicsEntity> {
        val comics: List<ComicsDetailsDTO> = dtoObject.dataDTO.results
        if (comics.isNullOrEmpty()) {
            return listOf()
        }
        val availableComics = dtoObject.dataDTO.total
        val comicEntities = mutableListOf<ComicsEntity>()

        comics.map {
            val resourcePath = getResourcePath(it)
            val comicEntity = getComic(availableComics.toInt(), availableComics, resourcePath, it)
            comicEntities.add(comicEntity)
        }
        return comicEntities
    }

    override fun mapDTOToEntityList(dtoObjectList: List<ComicsResponseDTO>): List<List<ComicsEntity>> {
        return dtoObjectList.map {
            mapDTOToEntity(it)
        }
    }

    // endregion

    // region Private Functions


    private fun getResourcePath(comic: ComicsDetailsDTO) =
        comic.thumbnailDTO.path.concat(comic.thumbnailDTO.extension)

    private fun getComic(
        entriesInList: Int,
        availableComics: String,
        resourcePath: String,
        comic: ComicsDetailsDTO
    ): ComicsEntity {
        return ComicsEntity(
            entriesInList,
            availableComics.toInt(),
            resourcePath,
            comic.title
        )
    }

    // endregion
}