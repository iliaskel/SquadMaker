package com.example.squadmaker.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "squad_table")
data class SquadEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val thumbnailPath: String
)