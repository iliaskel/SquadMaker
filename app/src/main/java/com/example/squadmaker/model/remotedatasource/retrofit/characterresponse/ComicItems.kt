package com.example.squadmaker.model.remotedatasource.retrofit.characterresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the information needed for the Comic Items that are related to a specific character.
 */
data class ComicItems(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)