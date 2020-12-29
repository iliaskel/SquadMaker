package com.example.squadmaker.model.remotedatasource.responses.creators

import com.google.gson.annotations.SerializedName

data class ThumbnailDTO(
	@SerializedName("path") val path: String,
	@SerializedName("extension") val extension: String
)