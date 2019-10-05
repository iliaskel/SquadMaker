package com.example.squadmaker.model.network.api

import com.example.squadmaker.model.network.response.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: String = "40",
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = "c260e787ab759bb0b83ad1d4d024de9c",
        @Query("hash") hash: String
    ): Response

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = "c260e787ab759bb0b83ad1d4d024de9c",
        @Query("hash") hash: String
    ): Response
}