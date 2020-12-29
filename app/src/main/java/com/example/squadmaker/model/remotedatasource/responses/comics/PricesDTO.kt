package com.example.squadmaker.model.remotedatasource.responses.comics

import com.google.gson.annotations.SerializedName

data class PricesDTO(
	@SerializedName("type") val type: String,
	@SerializedName("price") val price: Double
)