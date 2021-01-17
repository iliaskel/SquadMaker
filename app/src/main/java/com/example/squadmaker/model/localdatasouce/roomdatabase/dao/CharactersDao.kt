package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characterList: List<CharacterEntity>)

    @Query("DELETE FROM characters_table WHERE :characterId = id")
    suspend fun deleteCharacterByCharacterId(characterId: Int)

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCharacters()

    @Transaction
    suspend fun replaceCharacterList(characterList: List<CharacterEntity>) {
        deleteAllCharacters()
        insertCharacterList(characterList)
    }

    @Query("SELECT * FROM characters_table WHERE :characterId = id LIMIT 1")
    fun getCharacterByCharacterId(characterId: Int): CharacterEntity?

    @Query("SELECT * FROM characters_table")
    fun getAllCharactersList(): List<CharacterEntity>

    @Query("SELECT * FROM characters_table WHERE :characterId = id")
    fun getCharacterByCharacterIdFlow(characterId: Int): Flow<CharacterEntity?>

    @Query("SELECT * FROM characters_table")
    fun getAllCharactersListFlow(): Flow<List<CharacterEntity>>
}