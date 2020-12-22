package com.example.squadmaker.viewmodel

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.view.uimodel.UIComic
import com.example.squadmaker.view.uimodel.UIDetailedCharacter

interface DetailedViewModel {

    /**
     * Updates the information about the selected character. These information includes the detailed information about the
     * specific character and the comics he is being shown.
     *
     * The App's logic allows only one entry at a time.
     *
     * @param id an [Int] of the selected character.
     */
    fun updateDetailedCharacter(id: Int)

    /**
     * Removes the information of the stored detailed character. This includes the removal of the character's details as well as
     * the comic information about this character.
     */
    fun removeDetailedCharacter()

    /**
     * Adds/Removes the selected character from the Squad list.
     *
     * @param isSquadMember a [Boolean] representing if the character is already a Squad member.
     */
    fun updateSquadList(isSquadMember: Boolean)

    /**
     * @return LiveData with [DetailedCharacterEntity] entries stored in the database
     */
    fun getDetailedCharacter(): LiveData<UIDetailedCharacter?>

    /**
     * @return LiveData with [ComicsEntity] entries stored in the database
     */
    fun getComics(): LiveData<List<UIComic>>
}