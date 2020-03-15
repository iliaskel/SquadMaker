package com.example.squadmaker.model.network.response

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the comic information of a specific character.
 */
data class Comics(
    @SerializedName("available") val available: String,
    @SerializedName("returned") val returned: String,
    @SerializedName("comicItems") val comicItems: List<ComicItems>
)