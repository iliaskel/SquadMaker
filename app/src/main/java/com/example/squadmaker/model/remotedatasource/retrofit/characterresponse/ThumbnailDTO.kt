package com.example.squadmaker.model.remotedatasource.retrofit.characterresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the information needed for a character's thumbnail.
 */
data class ThumbnailDTO(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)