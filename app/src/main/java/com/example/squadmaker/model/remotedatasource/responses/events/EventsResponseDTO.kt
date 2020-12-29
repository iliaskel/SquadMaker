package com.example.squadmaker.model.remotedatasource.responses.events

import com.google.gson.annotations.SerializedName

data class EventsResponseDTO(
	@SerializedName("code") val code: Int,
	@SerializedName("status") val status: String,
	@SerializedName("data") val eventsData: EventsDataDTO
)