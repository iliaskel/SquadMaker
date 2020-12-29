package com.example.squadmaker.model.remotedatasource.responses.comics

import com.google.gson.annotations.SerializedName

data class ComicsDataDTO(
	@SerializedName("limit") val limit: Int,
	@SerializedName("total") val total: Int,
	@SerializedName("results") val comicsResultsDTOList: List<ComicsResultsDTO>
)