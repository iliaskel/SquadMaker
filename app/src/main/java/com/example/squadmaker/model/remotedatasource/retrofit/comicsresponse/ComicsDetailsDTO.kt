package com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse

import com.example.squadmaker.model.remotedatasource.retrofit.characterresponse.ThumbnailDTO
import com.google.gson.annotations.SerializedName

/**
 * Data class representing the information that are fetched from the Marvel API regarding the
 * comics that a specific character is involved.
 */
data class ComicsDetailsDTO(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnailDTO: ThumbnailDTO
)