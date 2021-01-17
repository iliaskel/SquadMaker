package com.example.squadmaker.model.repository.mapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO

interface Mapper {

    fun mapToCharacterEntity(characterDTO: CharacterResultsDTO): CharacterEntity
    fun mapToCharacterEntityList(characterDTOList: List<CharacterResultsDTO>): List<CharacterEntity>


    fun mapToComicEntity(comicsResponseDTO: ComicsResponseDTO): List<ComicsEntity>
    fun mapToComicEntityList(comicsResponseDTOList: List<ComicsResponseDTO>): List<List<ComicsEntity>>

    fun mapDetailedCharacterEntityToSquadEntity(characterEntity: CharacterEntity): SquadEntity
    fun mapDetailedCharacterEntityListToSquadEntityList(detailedCharacterEntityList: List<CharacterEntity>): List<SquadEntity>
}