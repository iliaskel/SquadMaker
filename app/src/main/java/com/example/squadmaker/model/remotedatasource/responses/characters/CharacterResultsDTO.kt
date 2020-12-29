package com.example.squadmaker.model.remotedatasource.responses.characters

import com.google.gson.annotations.SerializedName


/**
 *Data class representing the information needed to present a specific character.
 */
data class CharacterResultsDTO(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("thumbnail") val thumbnailDTO: ThumbnailDTO
)