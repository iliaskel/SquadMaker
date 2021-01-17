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
 * API under the -stories- resources
 *
 * These are the three parameters needed by every call in order to authenticate successfully.
 * @param ts a [String] with the current timestamp.
 * @param apiKey a [String] with the public Api key used for the query.
 * @param hash a [String] with the Md5 hash used for security reasons
 */

interface StoriesApi {

    @GET("v1/public/stories")
    suspend fun getStories(
        @Query("limit") limit: String = "20",
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): StoriesResponseDTO

    @GET("/v1/public/stories/{storyId}")
    suspend fun getStoryById(
        @Path("storyId") storyId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): StoriesResponseDTO

    @GET("/v1/public/stories/{storyId}/characters")
    suspend fun getCharactersForStoryId(
        @Path("storyId") storyId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CharactersResponseDTO

    @GET("/v1/public/stories/{storyId}/comics")
    suspend fun getComicsForStoryId(
        @Path("storyId") storyId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): ComicsResponseDTO

    @GET("/v1/public/stories/{storyId}/creators")
    suspend fun getCreatorsForStoryId(
        @Path("storyId") storyId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CreatorsResponseDTO

    @GET("/v1/public/stories/{storyId}/events")
    suspend fun getEventsForStoryId(
        @Path("storyId") storyId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): EventsResponseDTO

    @GET("/v1/public/stories/{storyId}/series")
    suspend fun getSeriesForStoryId(
        @Path("storyId") storyId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): SeriesResponseDTO
}