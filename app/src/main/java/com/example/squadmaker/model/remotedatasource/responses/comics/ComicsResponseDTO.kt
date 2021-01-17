package com.example.squadmaker.model.remotedatasource.responses.comics

import com.google.gson.annotations.SerializedName

data class ComicsResponseDTO(
	@SerializedName("code") val code: Int,
	@SerializedName("status") val status: String,
	@SerializedName("data") val comicsDataDTO: ComicsDataDTO
)