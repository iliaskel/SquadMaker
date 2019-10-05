package com.example.squadmaker.model.network.response

import com.google.gson.annotations.SerializedName

data class ComicItems(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)