package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series_table")
data class SeriesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val resourceURI: String,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val type: String,
    val modified: String,
    val thumbnail: String,
    val next: String,
    val previous: String
)