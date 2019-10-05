package com.example.squadmaker.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val thumbnailPath: String
)