package com.example.squadmaker.model.repository.mapper.dtotoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.repository.utils.concat
import javax.inject.Inject

class DetailedCharacterMapper
@Inject
constructor() : DTOToEntityMapper<CharacterResultsDTO, DetailedCharacterEntity> {

    // region Implements Mapper

    override fun mapDTOToEntity(
        dtoObject: CharacterResultsDTO
    ): DetailedCharacterEntity {
        val thumbnailPath =
            dtoObject.thumbnailDTO.path.concat(dtoObject.thumbnailDTO.extension)

        return DetailedCharacterEntity(
            dtoObject.id.toInt(),
            dtoObject.name,
            dtoObject.description,
            thumbnailPath,
            false
        )
    }

    override fun mapDTOToEntityList(dtoObjectList: List<CharacterResultsDTO>): List<DetailedCharacterEntity> {
        return dtoObjectList.map {
            mapDTOToEntity(it)
        }
    }

    // endregion
}