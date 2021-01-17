package com.example.squadmaker.model.remotedatasource.apis

import com.example.squadmaker.BuildConfig
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
 * API under the -creators- resources
 *
 * These are the three parameters needed by every call in order to authenticate successfully.
 * @param ts a [String] with the current timestamp.
 * @param apiKey a [String] with the public Api key used for the query.
 * @param hash a [String] with the Md5 hash used for security reasons
 */

interface CreatorsApi {

    @GET("v1/public/creators")
    suspend fun getCreators(
        @Query("limit") limit: String = "20",
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CreatorsResponseDTO

    @GET("/v1/public/creators/{creatorId}")
    suspend fun getCreatorById(
        @Path("creatorId") creatorId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CreatorsResponseDTO

    @GET("/v1/public/creators/{creatorId}/comics")
    suspend fun getComicsForCreatorId(
        @Path("creatorId") creatorId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): ComicsResponseDTO

    @GET("/v1/public/creators/{creatorId}/events")
    suspend fun getEventsForCreatorId(
        @Path("creatorId") creatorId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): EventsResponseDTO

    @GET("/v1/public/creators/{creatorId}/series")
    suspend fun getSeriesForCreatorId(
        @Path("creatorId") creatorId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): SeriesResponseDTO

    @GET("/v1/public/creators/{creatorId}/stories")
    suspend fun getStoriesForCreatorId(
        @Path("creatorId") creatorId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): StoriesResponseDTO
}