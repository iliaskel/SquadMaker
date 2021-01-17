package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(series: SeriesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeriesList(seriesList: List<SeriesEntity>)

    @Query("DELETE FROM series_table WHERE :seriesId = id")
    suspend fun deleteSeriesBySeriesId(seriesId: Int)

    @Query("DELETE FROM series_table")
    suspend fun deleteAllSeries()

    @Transaction
    suspend fun replaceSeriesList(seriesList: List<SeriesEntity>) {
        deleteAllSeries()
        insertSeriesList(seriesList)
    }

    @Query("SELECT * FROM series_table WHERE :seriesId = id LIMIT 1")
    fun getCharacterByCharacterId(seriesId: Int): SeriesEntity?

    @Query("SELECT * FROM series_table")
    fun getAllSeriesList(): List<SeriesEntity>

    @Query("SELECT * FROM series_table WHERE :seriesId= id")
    fun getSeriesBySeriesIdFlow(seriesId: Int): Flow<SeriesEntity?>

    @Query("SELECT * FROM series_table")
    fun getAllSeriesListFlow(): Flow<List<SeriesEntity>>
}