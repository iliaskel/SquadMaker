package com.example.squadmaker.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.squadmaker.model.database.entity.ComicsEntity

@Dao
interface ComicsDao {

    @Query("SELECT * FROM comics_table")
    fun getComics(): LiveData<List<ComicsEntity>>

    @Query("SELECT * FROM comics_table")
    suspend fun getComicsEntities(): List<ComicsEntity>

    @Transaction
    suspend fun replaceComics(comicsList: List<ComicsEntity>) {
        deleteComics()
        insertComics(comicsList)
    }

    @Query("DELETE  FROM comics_table")
    suspend fun deleteComics()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(comicsList: List<ComicsEntity>)
}