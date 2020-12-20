package com.example.squadmaker.model.repository.mapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsResponseDTO
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.EntityToEntityMapper
import javax.inject.Inject

class MapperImpl
@Inject
constructor(
    private val detailedCharacterMapper: DTOToEntityMapper<CharacterDTO, DetailedCharacterEntity>,
    private val characterMapper: DTOToEntityMapper<CharacterDTO, CharacterEntity>,
    private val comicsMapper: DTOToEntityMapper<ComicsResponseDTO, List<ComicsEntity>>,
    private val squadMapper: EntityToEntityMapper<DetailedCharacterEntity, SquadEntity>
) : Mapper {
    override fun mapToCharacterEntity(characterDTO: CharacterDTO): CharacterEntity {
        return characterMapper.mapDTOToEntity(characterDTO)
    }

    override fun mapToCharacterEntityList(characterDTOList: List<CharacterDTO>): List<CharacterEntity> {
        return characterMapper.mapDTOToEntityList(characterDTOList)
    }

    override fun mapToDetailedCharacter(characterDTO: CharacterDTO): DetailedCharacterEntity {
        return detailedCharacterMapper.mapDTOToEntity(characterDTO)
    }

    override fun mapToDetailedCharacter(characterDTOList: List<CharacterDTO>): List<DetailedCharacterEntity> {
        return detailedCharacterMapper.mapDTOToEntityList(characterDTOList)
    }

    override fun mapToComicEntity(comicsResponseDTO: ComicsResponseDTO): List<ComicsEntity> {
        return comicsMapper.mapDTOToEntity(comicsResponseDTO)
    }

    override fun mapToComicEntityList(comicsResponseDTOList: List<ComicsResponseDTO>): List<List<ComicsEntity>> {
        return comicsMapper.mapDTOToEntityList(comicsResponseDTOList)
    }

    override fun mapDetailedCharacterEntityToSquadEntity(detailedCharacterEntity: DetailedCharacterEntity): SquadEntity {
        return squadMapper.mapEntityToEntity(detailedCharacterEntity)
    }

    override fun mapDetailedCharacterEntityListToSquadEntityList(detailedCharacterEntityList: List<DetailedCharacterEntity>): List<SquadEntity> {
        return squadMapper.mapEntityListToEntityList(detailedCharacterEntityList)
    }
}