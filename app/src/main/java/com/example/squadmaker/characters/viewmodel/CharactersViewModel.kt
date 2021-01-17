package com.example.squadmaker.characters.viewmodel

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.characters.uimodel.UICharacter
import com.example.squadmaker.characters.uimodel.UISquadEntry

interface CharactersViewModel {

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
    fun getSquad(): LiveData<List<UISquadEntry>>
}