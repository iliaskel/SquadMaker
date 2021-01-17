package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creators_table")
data class CreatorEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val suffix: String,
    val fullName: String,
    val modified: String,
    val thumbnailPath: String,
    val resourceURI: String
)