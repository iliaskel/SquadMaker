package com.example.squadmaker.model.remotedatasource.responses.comics

import com.google.gson.annotations.SerializedName

data class ComicsResultsDTO(
	@SerializedName("id") val id: Int,
	@SerializedName("digitalId") val digitalId: Int,
	@SerializedName("title") val title: String,
	@SerializedName("issueNumber") val issueNumber: Int,
	@SerializedName("description") val description: String,
	@SerializedName("modified") val modified: String,
	@SerializedName("format") val format: String,
	@SerializedName("pageCount") val pageCount: Int,
	@SerializedName("resourceURI") val resourceURI: String,
	@SerializedName("prices") val pricesList: List<PricesDTO>,
	@SerializedName("thumbnail") val thumbnailDTO: ThumbnailDTO,
)