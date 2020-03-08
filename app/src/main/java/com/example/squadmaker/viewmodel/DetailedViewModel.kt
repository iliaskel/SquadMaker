package com.example.squadmaker.viewmodel

interface DetailedViewModel {
    fun updateDetailedCharacter(id: Int)
    fun removeDetailedCharacter()
    fun updateSquadList(isSquadMember: Boolean)
}