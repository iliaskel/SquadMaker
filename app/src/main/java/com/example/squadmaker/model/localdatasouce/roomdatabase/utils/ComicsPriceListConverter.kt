package com.example.squadmaker.model.localdatasouce.roomdatabase.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ComicsPriceListConverter {
    @TypeConverter
    fun fromMap(pricesMap: Map<String, Float>): String {
        val gson = Gson()
        return gson.toJson(pricesMap)
    }

    @TypeConverter
    fun fromString(value: String): Map<String, Float> {
        val mapType = object : TypeToken<Map<String, Float>>() {}.type
        return Gson().fromJson(value, mapType)
    }
}