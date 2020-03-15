package com.example.squadmaker.model.network.comicsresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the thumbnail information for a specific comic
 */
data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)