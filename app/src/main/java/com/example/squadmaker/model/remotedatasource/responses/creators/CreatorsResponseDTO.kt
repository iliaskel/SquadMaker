package com.example.squadmaker.model.remotedatasource.responses.creators

import com.google.gson.annotations.SerializedName

data class CreatorsResponseDTO(
	@SerializedName("code") val code: Int,
	@SerializedName("status") val status: String,
	@SerializedName("copyright") val copyright: String,
	@SerializedName("attributionText") val attributionText: String,
	@SerializedName("attributionHTML") val attributionHTML: String,
	@SerializedName("etag") val etag: String,
	@SerializedName("data") val creatorsDataDTO: CreatorsDataDTO
)