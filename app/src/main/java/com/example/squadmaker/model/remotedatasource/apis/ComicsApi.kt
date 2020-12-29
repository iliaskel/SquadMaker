package com.example.squadmaker.model.remotedatasource.apis

import com.example.squadmaker.BuildConfig
import com.example.squadmaker.model.remotedatasource.responses.characters.CharactersResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.creators.CreatorsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.events.EventsResponseDTO
import com.example.squadmaker.model.remotedatasource.responses.stories.StoriesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * An interface representing all the calls that the application will do to interact with the Marvel's
 * API under the -comics- resources
 *
 * These are the three parameters needed by every call in order to authenticate successfully.
 * @param ts a [String] with the current timestamp.
 * @param apiKey a [String] with the public Api key used for the query.
 * @param hash a [String] with the Md5 hash used for security reasons
 */

interface ComicsApi {

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("limit") limit: String = "20",
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): ComicsResponseDTO

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): ComicsResponseDTO

    @GET("/v1/public/comics/{comicId}/characters")
    suspend fun getCharactersForComicId(
        @Path("comicId") comicId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CharactersResponseDTO

    @GET("/v1/public/comics/{comicId}/events")
    suspend fun getEventsForComicId(
        @Path("comicId") comicId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): EventsResponseDTO

    @GET("/v1/public/comics/{comicId}/creators")
    suspend fun getCreatorsForComicId(
        @Path("comicId") comicId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): CreatorsResponseDTO

    @GET("/v1/public/comics/{comicId}/stories")
    suspend fun getStoriesForComicId(
        @Path("comicId") comicId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String = BuildConfig.PUBLIC_API_KEY,
        @Query("hash") hash: String
    ): StoriesResponseDTO
}