package com.example.squadmaker.model.remotedatasource.responses.characters

import com.google.gson.annotations.SerializedName

/**
 * Data class with the data field from the [CharactersResponseDTO] of the Marvel API.
 */
data class CharactersDataDTO(
    @SerializedName("limit") val limit: String,
    @SerializedName("total") val total: String,
    @SerializedName("results") val charactersResultsDTOList: List<CharacterResultsDTO>
)