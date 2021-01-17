package com.example.squadmaker.model.remotedatasource.responses.series

import com.google.gson.annotations.SerializedName

data class SeriesDataDTO(
	@SerializedName("offset") val offset: Int,
	@SerializedName("limit") val limit: Int,
	@SerializedName("total") val total: Int,
	@SerializedName("count") val count: Int,
	@SerializedName("results") val seriesResultsDTOList: List<SeriesResultsDTO>
)