package com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the thumbnail information for a specific comic
 */
data class ThumbnailDTO(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)