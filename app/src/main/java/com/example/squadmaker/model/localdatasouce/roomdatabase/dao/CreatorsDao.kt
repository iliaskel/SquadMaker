package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CreatorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CreatorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreator(creator: CreatorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreatorsList(creatorsList: List<CreatorEntity>)

    @Query("DELETE FROM creators_table WHERE :creatorId = id")
    suspend fun deleteCreatorByCreatorId(creatorId: Int)

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCreators()

    @Transaction
    suspend fun replaceCreatorList(creatorsList: List<CreatorEntity>) {
        deleteAllCreators()
        insertCreatorsList(creatorsList)
    }

    @Query("SELECT * FROM creators_table WHERE :creatorId = id LIMIT 1")
    fun getCreatorByCreatorId(creatorId: Int): CreatorEntity?

    @Query("SELECT * FROM creators_table")
    fun getAllCreatorsList(): List<CreatorEntity>

    @Query("SELECT * FROM creators_table WHERE :creatorId = id")
    fun getCreatorByCreatorIdFlow(creatorId: Int): Flow<CreatorEntity?>

    @Query("SELECT * FROM creators_table")
    fun getAllCreatorsListFlow(): Flow<List<CreatorEntity>>
}