package com.example.squadmaker.model.remotedatasource.responses.stories

import com.google.gson.annotations.SerializedName

data class StoriesDataDTO(
	@SerializedName("offset") val offset: Int,
	@SerializedName("limit") val limit: Int,
	@SerializedName("total") val total: Int,
	@SerializedName("count") val count: Int,
	@SerializedName("results") val storiesResultsDTOList: List<StoriesResultsDTO>
)

