package com.example.squadmaker.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.squadmaker.model.database.entity.CharacterEntity

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters_table")
    fun getAllCharacters(): LiveData<List<CharacterEntity>>

    @Transaction
    suspend fun deleteAndInsertCharacters(characterList: List<CharacterEntity>) {
        deleteCharacters()
        insertCharacters(characterList)
    }

    @Query("DELETE FROM characters_table")
    suspend fun deleteCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterList: List<CharacterEntity>)
}