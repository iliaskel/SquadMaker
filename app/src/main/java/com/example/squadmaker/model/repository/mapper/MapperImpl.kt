package com.example.squadmaker.model.repository.mapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.EntityToEntityMapper
import javax.inject.Inject

class MapperImpl
@Inject
constructor(
    private val detailedCharacterMapper: DTOToEntityMapper<CharacterResultsDTO, DetailedCharacterEntity>,
    private val characterMapper: DTOToEntityMapper<CharacterResultsDTO, CharacterEntity>,
    private val comicsMapper: DTOToEntityMapper<ComicsResponseDTO, List<ComicsEntity>>,
    private val squadMapper: EntityToEntityMapper<DetailedCharacterEntity, SquadEntity>
) : Mapper {
    override fun mapToCharacterEntity(characterDTO: CharacterResultsDTO): CharacterEntity {
        return characterMapper.mapDTOToEntity(characterDTO)
    }

    override fun mapToCharacterEntityList(characterDTOList: List<CharacterResultsDTO>): List<CharacterEntity> {
        return characterMapper.mapDTOToEntityList(characterDTOList)
    }

    override fun mapToDetailedCharacter(characterDTO: CharacterResultsDTO): DetailedCharacterEntity {
        return detailedCharacterMapper.mapDTOToEntity(characterDTO)
    }

    override fun mapToDetailedCharacter(characterDTOList: List<CharacterResultsDTO>): List<DetailedCharacterEntity> {
        return detailedCharacterMapper.mapDTOToEntityList(characterDTOList)
    }

    override fun mapToComicEntityList(comicsResponseDTOList: List<ComicsResponseDTO>): List<List<ComicsEntity>> {
        return comicsMapper.mapDTOToEntityList(comicsResponseDTOList)

    }

    override fun mapToComicEntity(comicsResponseDTO: ComicsResponseDTO): List<ComicsEntity> {
        return comicsMapper.mapDTOToEntity(comicsResponseDTO)
    }

    override fun mapDetailedCharacterEntityToSquadEntity(detailedCharacterEntity: DetailedCharacterEntity): SquadEntity {
        return squadMapper.mapEntityToEntity(detailedCharacterEntity)
    }

    override fun mapDetailedCharacterEntityListToSquadEntityList(detailedCharacterEntityList: List<DetailedCharacterEntity>): List<SquadEntity> {
        return squadMapper.mapEntityListToEntityList(detailedCharacterEntityList)
    }
}