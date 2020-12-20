package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters_table")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Transaction
    suspend fun replaceCharacterList(characterList: List<CharacterEntity>) {
        deleteCharacters()
        insertCharacters(characterList)
    }

    @Query("DELETE FROM characters_table")
    suspend fun deleteCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characterList: List<CharacterEntity>)
}