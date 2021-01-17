package com.example.squadmaker.model.remotedatasource.responses.series

import com.google.gson.annotations.SerializedName

data class SeriesResultsDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("startYear") val startYear: Int,
    @SerializedName("endYear") val endYear: Int,
    @SerializedName("rating") val rating: String,
    @SerializedName("type") val type: String,
    @SerializedName("modified") val modified: String,
    @SerializedName("thumbnail") val thumbnail: ThumbnailDTO,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: String
)