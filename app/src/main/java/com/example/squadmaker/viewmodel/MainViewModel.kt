package com.example.squadmaker.viewmodel

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity

interface MainViewModel {

    /**
     * Fetches a list of characters from the Marvel API
     */
    fun fetchCharacters()

    /**
     * @return [LiveData] of [CharacterEntity] list
     */
    fun getCharacters(): LiveData<List<CharacterEntity>>

    /**
     * @return [LiveData] of [SquadEntity] list
     */
    fun getSquad(): LiveData<List<SquadEntity>>
}