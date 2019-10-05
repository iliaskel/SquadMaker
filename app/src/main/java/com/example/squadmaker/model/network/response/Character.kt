package com.example.squadmaker.model.network.response

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail,
    @SerializedName("comics") val comics: Comics
)