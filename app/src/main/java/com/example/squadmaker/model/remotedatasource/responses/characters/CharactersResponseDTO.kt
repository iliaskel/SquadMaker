package com.example.squadmaker.model.remotedatasource.responses.characters

import com.google.gson.annotations.SerializedName

/**
 * Data class with the [CharactersResponseDTO] from the Marvel Api for the characters.
 */
data class CharactersResponseDTO(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val charactersDataDTO: CharactersDataDTO
)