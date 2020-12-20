package com.example.squadmaker.repository.mapper.dtotoentitymapper

interface DTOToEntityMapper<DTO, Entity> {

    fun mapDTOToEntity(dtoObject: DTO): Entity

    fun mapDTOToEntityList(dtoObjectList: List<DTO>): List<Entity>
}