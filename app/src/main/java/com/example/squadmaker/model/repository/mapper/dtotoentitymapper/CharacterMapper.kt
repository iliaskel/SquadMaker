package com.example.squadmaker.model.repository.mapper.dtotoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.repository.utils.concat
import javax.inject.Inject

class CharacterMapper
@Inject
constructor() : DTOToEntityMapper<CharacterResultsDTO, CharacterEntity> {

    // region Implements Mapper

    override fun mapDTOToEntity(dtoObject: CharacterResultsDTO): CharacterEntity {
        val id = dtoObject.id.toInt()
        val name = dtoObject.name
        val thumbnailStringUri =
            dtoObject.thumbnailDTO.path.concat(dtoObject.thumbnailDTO.extension)

        return CharacterEntity(id, name, thumbnailStringUri)
    }

    override fun mapDTOToEntityList(dtoObjectList: List<CharacterResultsDTO>): List<CharacterEntity> {
        return dtoObjectList.map {
            mapDTOToEntity(it)
        }
    }

    // endregion
}