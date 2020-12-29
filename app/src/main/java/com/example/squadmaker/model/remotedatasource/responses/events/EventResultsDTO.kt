package com.example.squadmaker.model.remotedatasource.responses.events

import com.google.gson.annotations.SerializedName

data class EventResultsDTO(
	@SerializedName("id") val id: Int,
	@SerializedName("title") val title: String,
	@SerializedName("description") val description: String,
	@SerializedName("modified") val modified: String,
	@SerializedName("start") val start: String,
	@SerializedName("end") val end: String,
	@SerializedName("thumbnail") val thumbnail: ThumbnailDTO
)