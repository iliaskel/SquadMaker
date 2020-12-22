package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface SquadDao {

    @Query("SELECT * FROM squad_table")
    fun getSquad(): Flow<List<SquadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSquadMember(squadEntity: SquadEntity)

    @Query("SELECT * FROM squad_table WHERE id=:id")
    suspend fun getSquadListForCharacterId(id: Int): List<SquadEntity>

    @Query("DELETE  FROM squad_table WHERE id=:id")
    suspend fun deleteSquadMember(id: Int)

}