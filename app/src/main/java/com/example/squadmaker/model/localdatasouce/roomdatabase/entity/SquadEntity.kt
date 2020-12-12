package com.example.squadmaker.model.localdatasouce.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing the stored information needed to present the SquadList in the Squad View.
 */
@Entity(tableName = "squad_table")
data class SquadEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val thumbnailPath: String
)