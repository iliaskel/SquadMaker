package com.example.squadmaker.model.remotedatasource.responses.events

import com.google.gson.annotations.SerializedName

data class EventsDataDTO (
	@SerializedName("offset") val offset : Int,
	@SerializedName("limit") val limit : Int,
	@SerializedName("total") val total : Int,
	@SerializedName("count") val count : Int,
	@SerializedName("results") val eventResultsDTOList : List<EventResultsDTO>
)