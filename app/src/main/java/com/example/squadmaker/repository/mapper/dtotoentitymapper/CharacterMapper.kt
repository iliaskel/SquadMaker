package com.example.squadmaker.repository.mapper.dtotoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.repository.utils.concat
import javax.inject.Inject

class CharacterMapper
@Inject
constructor() : DTOToEntityMapper<CharacterDTO, CharacterEntity> {

    // region Implements Mapper

    override fun mapDTOToEntity(dtoObject: CharacterDTO): CharacterEntity {
        val id = dtoObject.id.toInt()
        val name = dtoObject.name
        val thumbnailStringUri =
            dtoObject.thumbnailDTO.path.concat(dtoObject.thumbnailDTO.extension)

        return CharacterEntity(id, name, thumbnailStringUri)
    }

    override fun mapDTOToEntityList(dtoObjectList: List<CharacterDTO>): List<CharacterEntity> {
        return dtoObjectList.map {
            mapDTOToEntity(it)
        }
    }

    // endregion
}