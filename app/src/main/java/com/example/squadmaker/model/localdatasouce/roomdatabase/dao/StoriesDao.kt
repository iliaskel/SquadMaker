package com.example.squadmaker.model.localdatasouce.roomdatabase.dao

import androidx.room.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.StoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(Story: StoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStoriesList(StoriesList: List<StoryEntity>)

    @Query("DELETE FROM story_table WHERE :storyId = id")
    suspend fun deleteStoryByStoryId(storyId: Int)

    @Query("DELETE FROM story_table")
    suspend fun deleteAllStories()

    @Transaction
    suspend fun replaceStoriesList(storiesList: List<StoryEntity>) {
        deleteAllStories()
        insertStoriesList(storiesList)
    }

    @Query("SELECT * FROM story_table WHERE :storyId = id LIMIT 1")
    fun getStoryByStoryId(storyId: Int): StoryEntity?

    @Query("SELECT * FROM story_table")
    fun getAllStoriesList(): List<StoryEntity>

    @Query("SELECT * FROM story_table WHERE :storyId = id")
    fun getCharacterByCharacterIdFlow(storyId: Int): Flow<StoryEntity?>

    @Query("SELECT * FROM story_table")
    fun getAllStoriesListFlow(): Flow<List<StoryEntity>>
}