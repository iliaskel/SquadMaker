package com.example.squadmaker.viewmodel

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
}