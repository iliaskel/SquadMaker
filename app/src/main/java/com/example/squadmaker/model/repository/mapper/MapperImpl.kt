package com.example.squadmaker.model.repository.mapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.EntityToEntityMapper
import javax.inject.Inject

class MapperImpl
@Inject
constructor(
    private val characterMapper: DTOToEntityMapper<CharacterResultsDTO, CharacterEntity>,
    private val comicsMapper: DTOToEntityMapper<ComicsResponseDTO, List<ComicsEntity>>,
    private val squadMapper: EntityToEntityMapper<CharacterEntity, SquadEntity>
) : Mapper {
    override fun mapToCharacterEntity(characterDTO: CharacterResultsDTO): CharacterEntity {
        return characterMapper.mapDTOToEntity(characterDTO)
    }

    override fun mapToCharacterEntityList(characterDTOList: List<CharacterResultsDTO>): List<CharacterEntity> {
        return characterMapper.mapDTOToEntityList(characterDTOList)
    }

    override fun mapToComicEntityList(comicsResponseDTOList: List<ComicsResponseDTO>): List<List<ComicsEntity>> {
        return comicsMapper.mapDTOToEntityList(comicsResponseDTOList)

    }

    override fun mapToComicEntity(comicsResponseDTO: ComicsResponseDTO): List<ComicsEntity> {
        return comicsMapper.mapDTOToEntity(comicsResponseDTO)
    }

    override fun mapDetailedCharacterEntityToSquadEntity(detailedCharacterEntity: CharacterEntity): SquadEntity {
        return squadMapper.mapEntityToEntity(detailedCharacterEntity)
    }

    override fun mapDetailedCharacterEntityListToSquadEntityList(detailedCharacterEntityList: List<CharacterEntity>): List<SquadEntity> {
        return squadMapper.mapEntityListToEntityList(detailedCharacterEntityList)
    }
}