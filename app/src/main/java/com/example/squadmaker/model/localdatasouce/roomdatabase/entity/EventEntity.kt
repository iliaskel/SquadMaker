package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_table")
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val modified: String,
    val start: String,
    val end: String,
    val thumbnail: String
)