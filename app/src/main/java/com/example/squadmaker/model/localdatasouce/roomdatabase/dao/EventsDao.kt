package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEventsList(eventsList: List<EventEntity>)

    @Query("DELETE FROM events_table WHERE :eventId = id")
    suspend fun deleteEventByEventId(eventId: Int)

    @Query("DELETE FROM events_table")
    suspend fun deleteAllEvents()

    @Transaction
    suspend fun replaceEventsList(eventsList: List<EventEntity>) {
        deleteAllEvents()
        insertEventsList(eventsList)
    }

    @Query("SELECT * FROM events_table WHERE :eventId = id LIMIT 1")
    fun getEventByEventId(eventId: Int): EventEntity?

    @Query("SELECT * FROM events_table")
    fun getAllEventsList(): List<EventEntity>

    @Query("SELECT * FROM events_table WHERE :eventId = id")
    fun getEventByEventIdFlow(eventId: Int): Flow<EventEntity?>

    @Query("SELECT * FROM events_table")
    fun getAllEventsListFlow(): Flow<List<EventEntity>>
}