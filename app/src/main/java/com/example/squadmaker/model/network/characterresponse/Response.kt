package com.example.squadmaker.model.network.characterresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class with the [Response] from the Marvel Api for the characters.
 */
data class Response(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: Data
)