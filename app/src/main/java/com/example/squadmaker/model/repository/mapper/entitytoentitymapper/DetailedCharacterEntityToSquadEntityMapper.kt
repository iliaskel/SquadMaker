package com.example.squadmaker.model.repository.mapper.entitytoentitymapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import javax.inject.Inject

class DetailedCharacterEntityToSquadEntityMapper
@Inject
constructor() : EntityToEntityMapper<DetailedCharacterEntity, SquadEntity> {

    override fun mapEntityToEntity(entity: DetailedCharacterEntity): SquadEntity {
        return SquadEntity(entity.id, entity.resourceUrl)
    }

    override fun mapEntityListToEntityList(entityList: List<DetailedCharacterEntity>): List<SquadEntity> {
        return entityList.map {
            mapEntityToEntity(it)
        }
    }
}