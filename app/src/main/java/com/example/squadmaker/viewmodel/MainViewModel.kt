package com.example.squadmaker.viewmodel

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.view.uimodel.UICharacter

interface MainViewModel {

    /**
     * Fetches a list of characters from the Marvel API
     */
    fun fetchCharacters()

    /**
     * @return [LiveData] of [CharacterEntity] list
     */
    fun getCharacters(): LiveData<List<UICharacter>>

    /**
     * @return [LiveData] of [SquadEntity] list
     */
    fun getSquad(): LiveData<List<SquadEntity>>
}