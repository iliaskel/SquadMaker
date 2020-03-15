package com.example.squadmaker.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing the stored details for a specific character.
 */
@Entity(tableName = "detailed_character_table")
data class DetailedCharacterEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val resourceUrl: String,
    val availableComics: Int,
    val isSquadMember: Boolean
)