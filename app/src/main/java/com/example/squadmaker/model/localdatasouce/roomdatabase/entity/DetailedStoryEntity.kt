package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailed_story_entity")
data class DetailedStoryEntity(
     @PrimaryKey(autoGenerate = false)
     val storyId: Int
)