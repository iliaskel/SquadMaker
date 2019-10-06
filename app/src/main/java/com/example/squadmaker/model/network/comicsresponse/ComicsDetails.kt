package com.example.squadmaker.model.network.comicsresponse

import com.example.squadmaker.model.network.response.Thumbnail
import com.google.gson.annotations.SerializedName

data class ComicsDetails(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
)