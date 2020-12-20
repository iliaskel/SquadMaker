package com.example.squadmaker.model.remotedatasource.retrofit.characterresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class with the data field from the [CharactersResponseDTO] of the Marvel API.
 */
data class DataDTO(
    @SerializedName("limit") val limit: String,
    @SerializedName("total") val total: String,
    @SerializedName("results") val results: List<CharacterDTO>
)