package com.example.squadmaker.repository

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity

interface Repository {

    /**
     * Fetches a list of Marvel characters and updates the already stored list.
     */
    suspend fun fetchAndSaveCharacters()

    /**
     * Fetches the selected character's details and updates the database with the updated information.
     *
     * The App's logic allows only one entry at a time.
     *
     * @param id an [Int] with the id of the selected character.
     */
    suspend fun fetchAndSaveDetailedCharacterById(id: Int)

    /**
     * Removes the currently saved character's details. This includes the details of the characters
     * and the comics thar this character is involved.
     */
    suspend fun removeDetailedCharacter()

    /**
     * Fetches the selected character's comics that she/he is involved and updates the database with the updated information.
     *
     * The App's logic allows only one entry at a time.
     *
     * @param id an [Int] with the id of the selected character.
     */
    suspend fun fetchAndSaveComicsByCharacterId(id: Int)

    /**
     * Updates the Squad list for the currently selected character.
     *
     * @param isSquadMember a [Boolean] representing if the character is already a Squad member.
     */
    suspend fun updateSquadEntry(isSquadMember: Boolean)

    /**
     * @return LiveData with [DetailedCharacterEntity] entry stored in the database
     */
    fun getDetailedCharacter(): LiveData<DetailedCharacterEntity>

    /**
     *@return LiveData list with [ComicsEntity] entries stored in the database
     */
    fun getComics(): LiveData<List<ComicsEntity>>
}