package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_table")
data class StoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String,
    val title: String,
    val resourceURI: String,
    val type: String,
    val modified: String,
    val thumbnail: String
)