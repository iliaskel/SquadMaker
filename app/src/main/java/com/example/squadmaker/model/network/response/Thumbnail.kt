package com.example.squadmaker.model.network.response

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the information needed for a character's thumbnail.
 */
data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)