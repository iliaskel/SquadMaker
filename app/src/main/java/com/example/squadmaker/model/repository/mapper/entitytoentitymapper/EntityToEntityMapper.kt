package com.example.squadmaker.model.repository.mapper.entitytoentitymapper

interface EntityToEntityMapper<SourceEntity, DestinationEntity> {

    fun mapEntityToEntity(entity: SourceEntity): DestinationEntity

    fun mapEntityListToEntityList(entityList: List<SourceEntity>): List<DestinationEntity>
}