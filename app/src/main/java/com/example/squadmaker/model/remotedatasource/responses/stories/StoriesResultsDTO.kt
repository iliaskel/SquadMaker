package com.example.squadmaker.model.remotedatasource.responses.stories

import com.google.gson.annotations.SerializedName

data class StoriesResultsDTO(
	@SerializedName("id") val id: Int,
	@SerializedName("title") val title: String,
	@SerializedName("description") val description: String,
	@SerializedName("resourceURI") val resourceURI: String,
	@SerializedName("type") val type: String,
	@SerializedName("modified") val modified: String,
	@SerializedName("thumbnail") val thumbnail: String
)