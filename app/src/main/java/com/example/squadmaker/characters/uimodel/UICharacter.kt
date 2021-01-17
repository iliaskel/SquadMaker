package com.example.squadmaker.characters.uimodel

import android.view.View

data class UICharacter constructor(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailPath: String,
    val isSquadMember: Boolean = false,
    val navigateToDetailedViewAction: (View) -> Unit
)