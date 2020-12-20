package com.example.squadmaker.repository.mapper

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.CharacterDTO
import com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse.ComicsResponseDTO

interface Mapper {

    fun mapToCharacterEntity(characterDTO: CharacterDTO): CharacterEntity
    fun mapToCharacterEntityList(characterDTOList: List<CharacterDTO>): List<CharacterEntity>

    fun mapToDetailedCharacter(characterDTO: CharacterDTO): DetailedCharacterEntity
    fun mapToDetailedCharacter(characterDTOList: List<CharacterDTO>): List<DetailedCharacterEntity>

    fun mapToComicEntity(comicsResponseDTO: ComicsResponseDTO): List<ComicsEntity>
    fun mapToComicEntityList(comicsResponseDTOList: List<ComicsResponseDTO>): List<List<ComicsEntity>>

    fun mapDetailedCharacterEntityToSquadEntity(detailedCharacterEntity: DetailedCharacterEntity): SquadEntity
    fun mapDetailedCharacterEntityListToSquadEntityList(detailedCharacterEntityList: List<DetailedCharacterEntity>): List<SquadEntity>
}