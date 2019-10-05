package com.example.squadmaker.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity


@Dao
interface SquadDao {

    @Query("SELECT * FROM squad_table")
    fun getSquad(): LiveData<List<SquadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSquadMember(character: CharacterEntity)

    @Query("SELECT * FROM squad_table WHERE id=:id")
    suspend fun isUserInSquad(id: Int): List<SquadEntity>

}