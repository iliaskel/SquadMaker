package com.example.squadmaker.model.localdatasouce

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import kotlinx.coroutines.flow.Flow

interface DetailedCharacterLocalDataSource {

    suspend fun replaceDetailedCharacter(detailedCharacterEntity: DetailedCharacterEntity)

    suspend fun deleteDetailedCharacter()

    suspend fun getDetailedCharacterEntity(): DetailedCharacterEntity

    fun getDetailedCharacter(): Flow<DetailedCharacterEntity?>
}