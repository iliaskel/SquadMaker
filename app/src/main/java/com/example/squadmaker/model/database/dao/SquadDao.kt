package com.example.squadmaker.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.squadmaker.model.database.entity.SquadEntity


@Dao
interface SquadDao {

    @Query("SELECT * FROM squad_table")
    fun getSquad(): LiveData<List<SquadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSquadMember(squadEntity: SquadEntity)

    @Query("SELECT * FROM squad_table WHERE id=:id")
    suspend fun isCharacterInSquad(id: Int): List<SquadEntity>

    @Query("DELETE  FROM squad_table WHERE id=:id")
    suspend fun deleteSquadMember(id: Int)

}