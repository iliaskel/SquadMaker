package com.example.squadmaker.model.localdatasouce

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity

interface DetailedCharacterLocalDataSource {

    suspend fun replaceDetailedCharacter(detailedCharacterEntity: DetailedCharacterEntity)

    suspend fun deleteDetailedCharacter()

    suspend fun getDetailedCharacterEntity(): DetailedCharacterEntity

    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity>
}