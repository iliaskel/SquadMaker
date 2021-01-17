package com.example.squadmaker.model.repository.mapper.dtotoentitymapper.comics

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.PricesDTO
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import com.example.squadmaker.model.repository.utils.concat
import javax.inject.Inject

class ComicsMapper
@Inject
constructor() : DTOToEntityMapper<ComicsResponseDTO, @JvmSuppressWildcards List<ComicsEntity>> {

    // region Implements Mapper

    override fun mapDTOToEntity(dtoObject: ComicsResponseDTO): List<ComicsEntity> {
        val comics: List<ComicsResultsDTO> = dtoObject.comicsDataDTO.comicsResultsDTOList
        if (comics.isNullOrEmpty()) {
            return listOf()
        }
        val comicEntities = mutableListOf<ComicsEntity>()

        comics.map {
            val resourcePath = getResourcePath(it)
            val comicEntity = getComic(resourcePath, it)
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


    private fun getResourcePath(comic: ComicsResultsDTO) =
        comic.thumbnailDTO.path.concat(comic.thumbnailDTO.extension)

    private fun getComic(
        resourcePath: String,
        comic: ComicsResultsDTO
    ): ComicsEntity {
        val pricesListMap = getPricesListMap(comic.pricesList)
        return ComicsEntity(
            id = comic.id,
            title = comic.title,
            digitalId = comic.digitalId,
            resourceURI = comic.resourceURI,
            issueNumber = comic.issueNumber,
            description = comic.description,
            modified = comic.modified,
            format = comic.format,
            pageCount = comic.pageCount,
            pricesList = pricesListMap,
            thumbnailPath = comic.thumbnailDTO.path.plus(comic.thumbnailDTO.extension)
        )
    }

    private fun getPricesListMap(pricesList: List<PricesDTO>): Map<String, Float> {
        val pricesListMap = mutableMapOf<String, Float>()
        pricesList.forEach {
            pricesListMap[it.type] = it.price.toFloat()
        }
        return pricesListMap
    }

    // endregion
}