package com.example.squadmaker.viewmodel

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity

interface MainViewModel {
    fun fetchCharacters()
    fun getCharacters(): LiveData<List<CharacterEntity>>
    fun getSquad(): LiveData<List<SquadEntity>>
}