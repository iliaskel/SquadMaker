package com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class with the [ComicsResponseDTO] from the Marvel Api regarding the comic information of a specific character.
 */
data class ComicsResponseDTO(
    @SerializedName("code") val code: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val dataDTO: DataDTO
)