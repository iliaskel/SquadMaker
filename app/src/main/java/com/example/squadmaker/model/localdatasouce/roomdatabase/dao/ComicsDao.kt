package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(comic: ComicsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComicList(comicsList: List<ComicsEntity>)

    @Query("DELETE FROM comics_table WHERE :comicId = id")
    suspend fun deleteComicByComicId(comicId: Int)

    @Query("DELETE FROM comics_table")
    suspend fun deleteAllComics()

    @Transaction
    suspend fun replaceComicsList(comicList: List<ComicsEntity>) {
        deleteAllComics()
        insertComicList(comicList)
    }

    @Query("SELECT * FROM comics_table WHERE :comicId = id LIMIT 1")
    fun getComicByComicId(comicId: Int): ComicsEntity?

    @Query("SELECT * FROM comics_table")
    fun getAllComicsList(): List<ComicsEntity>

    @Query("SELECT * FROM comics_table WHERE :comicId = id")
    fun getComicByComicIdFlow(comicId: Int): Flow<ComicsEntity?>

    @Query("SELECT * FROM comics_table")
    fun getAllComicsListFlow(): Flow<List<ComicsEntity>>
}