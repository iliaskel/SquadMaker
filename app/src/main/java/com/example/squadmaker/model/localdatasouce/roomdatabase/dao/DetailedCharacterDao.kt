package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity

@Dao
interface DetailedCharacterDao {

    @Query("SELECT * FROM detailed_character_table LIMIT 1")
    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity>

    @Query("SELECT * FROM detailed_character_table LIMIT 1")
    suspend fun getDetailedCharacterEntity(): DetailedCharacterEntity

    @Transaction
    suspend fun replaceDetailedCharacter(detailedCharacter: DetailedCharacterEntity) {
        deleteDetailedCharacters()
        insertDetailedCharacter(detailedCharacter)
    }

    @Query("DELETE  FROM detailed_character_table")
    suspend fun deleteDetailedCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailedCharacter(detailedCharacter: DetailedCharacterEntity)

}