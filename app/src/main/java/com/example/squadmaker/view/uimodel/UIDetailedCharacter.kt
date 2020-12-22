package com.example.squadmaker.view.uimodel

data class UIDetailedCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val resourceUrl: String,
    val availableComics: Int,
    val isSquadMember: Boolean
)