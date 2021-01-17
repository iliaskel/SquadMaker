package com.example.squadmaker.model.repository.mapper.entitytoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import javax.inject.Inject

class CharacterEntityToSquadEntityMapper
@Inject
constructor() : EntityToEntityMapper<CharacterEntity, SquadEntity> {

    override fun mapEntityToEntity(entity: CharacterEntity): SquadEntity {
        return SquadEntity(entity.id, entity.thumbnailStringUrl)
    }

    override fun mapEntityListToEntityList(entityList: List<CharacterEntity>): List<SquadEntity> {
        return entityList.map {
            mapEntityToEntity(it)
        }
    }
}