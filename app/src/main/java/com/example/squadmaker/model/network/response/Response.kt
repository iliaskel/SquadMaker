package com.example.squadmaker.model.network.response

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: Data
)