package com.example.squadmaker.model.remotedatasource.apis

import com.example.squadmaker.BuildConfig
import com.example.squadmaker.model.remotedatasource.responses.characters.CharactersResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.creators.CreatorsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.events.EventsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.series.SeriesResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.stories.StoriesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * An interface representing all the calls that the application will do to interact with the Marvel's
 * API under the -events- resources
 *
 * These are the three parameters needed by every call in order to authenticate successfully.
 * @param ts a [String] with the current timestamp.
 * @param apiKey a [String] with the public Api key used for the query.
 * @param hash a [String] with the Md5 hash used for security reasons
 */

interface EventsApi {

    @GET("v1/public/events")
    suspend fun getEvents(
        @Query("limit") limit: String = "20",
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): EventsResponseDTO

    @GET("/v1/public/events/{eventId}")
    suspend fun getEventById(
        @Path("eventId") eventId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): EventsResponseDTO

    @GET("/v1/public/events/{eventId}/characters")
    suspend fun getCharactersForEventId(
        @Path("eventId") eventId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CharactersResponseDTO

    @GET("/v1/public/events/{eventId}/comics")
    suspend fun getComicsForEventId(
        @Path("eventId") eventId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): ComicsResponseDTO

    @GET("/v1/public/events/{eventId}/creators")
    suspend fun getCreatorsForEventId(
        @Path("eventId") eventId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CreatorsResponseDTO

    @GET("/v1/public/events/{eventId}/series")
    suspend fun getSeriesForEventId(
        @Path("eventId") eventId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): SeriesResponseDTO

    @GET("/v1/public/events/{eventId}/stories")
    suspend fun getStoriesForEventId(
        @Path("eventId") eventId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): StoriesResponseDTO
}