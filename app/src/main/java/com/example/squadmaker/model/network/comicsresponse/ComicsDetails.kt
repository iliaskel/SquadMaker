package com.example.squadmaker.model.network.comicsresponse

import com.example.squadmaker.model.network.characterresponse.Thumbnail
import com.google.gson.annotations.SerializedName

/**
 * Data class representing the information that are fetched from the Marvel API regarding the
 * comics that a specific character is involved.
 */
data class ComicsDetails(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
)