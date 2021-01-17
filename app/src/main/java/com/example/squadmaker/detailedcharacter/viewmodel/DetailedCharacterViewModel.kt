package com.example.squadmaker.detailedcharacter.viewmodel

import androidx.lifecycle.LiveData
import com.example.squadmaker.characters.uimodel.UICharacter
import com.example.squadmaker.detailedcharacter.uimodel.UIComic
import com.example.squadmaker.detailedcharacter.uimodel.UIDetailedCharacter
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity

interface DetailedCharacterViewModel {

    /**
     * @return LiveData with [UICharacter] entries stored in the database
     */
    fun getDetailedCharacter(characterId: Int): LiveData<UIDetailedCharacter?>

    /**
     * @return LiveData with [ComicsEntity] entries stored in the database
     */
    fun getComics(): LiveData<List<UIComic>>
}