package com.example.squadmaker.model.remotedatasource.retrofit.comicsresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the data field included in the comics [ComicsResponseDTO] of the Marvel Api.
 */
data class DataDTO(
    @SerializedName("limit") val limit: String,
    @SerializedName("total") val total: String,
    @SerializedName("results") val results: List<ComicsDetailsDTO>
)