package com.example.squadmaker.model.network.comicsresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class with the [Response] from the Marvel Api regarding the comic information of a specific character.
 */
data class Response(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: Data
)