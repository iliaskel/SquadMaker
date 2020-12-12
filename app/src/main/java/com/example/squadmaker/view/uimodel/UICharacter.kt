package com.example.squadmaker.view.uimodel

import android.view.View

data class UICharacter(
    val id: Int,
    val name: String,
    val thumbnailPath: String,
    val navigateToDetailedViewAction: (View) -> Unit
)