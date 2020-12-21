package com.example.squadmaker.model.repository.mapper.dtotoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.model.repository.utils.concat
import javax.inject.Inject

class DetailedCharacterMapper
@Inject
constructor() : DTOToEntityMapper<CharacterDTO, DetailedCharacterEntity> {

    // region Implements Mapper

    override fun mapDTOToEntity(
        dtoObject: CharacterDTO
    ): DetailedCharacterEntity {
        val thumbnailPath =
            dtoObject.thumbnailDTO.path.concat(dtoObject.thumbnailDTO.extension)

        return DetailedCharacterEntity(
            dtoObject.id.toInt(),
            dtoObject.name,
            dtoObject.description,
            thumbnailPath,
            dtoObject.comicsDTO.available.toInt(),
            false
        )
    }

    override fun mapDTOToEntityList(dtoObjectList: List<CharacterDTO>): List<DetailedCharacterEntity> {
        return dtoObjectList.map {
            mapDTOToEntity(it)
        }
    }

    // endregion
}