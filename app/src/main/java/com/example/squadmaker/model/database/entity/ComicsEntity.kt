package com.example.squadmaker.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing the stored information about the comics that a specific character is involved.
 */
@Entity(tableName = "comics_table")
data class ComicsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val availableComics: Int,
    val resourceUri: String,
    val name: String
)