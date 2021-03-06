package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing the stored information about the comics that a specific character is involved.
 */
@Entity(tableName = "comics_table")
data class ComicsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Int,
    val description: String,
    val modified: String,
    val format: String,
    val pageCount: Int,
    val resourceURI: String,
    val pricesList: Map<String, Float>,
    val thumbnailPath: String
)