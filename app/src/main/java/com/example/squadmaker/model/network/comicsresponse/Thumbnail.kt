package com.example.squadmaker.model.network.comicsresponse

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
)