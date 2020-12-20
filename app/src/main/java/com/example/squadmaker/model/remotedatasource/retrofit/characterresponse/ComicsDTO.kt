package com.example.squadmaker.model.remotedatasource.retrofit.characterresponse

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the comic information of a specific character.
 */
data class ComicsDTO(
    @SerializedName("available") val available: String,
    @SerializedName("returned") val returned: String,
    @SerializedName("comicItems") val comicItemDTOS: List<ComicItemsDTO>
)