package com.example.squadmaker.characters.uimodel

import android.view.View

data class UISquadEntry(
    val id: Int,
    val thumbnailPath: String,
    val navigateToDetailedViewAction: (View) -> Unit
)