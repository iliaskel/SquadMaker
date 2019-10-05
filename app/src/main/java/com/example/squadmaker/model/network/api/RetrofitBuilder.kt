package com.example.squadmaker.model.network.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val MARVEL_API_BASE_URL: String = "https://gateway.marvel.com:443/"

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }
    val marvelApiService: MarvelApiService by lazy {

        retrofitBuilder
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor().build())
            .build()
            .create(MarvelApiService::class.java)
    }

    private fun getHttpClientInterceptor(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        return httpClient.addInterceptor(logging)
    }
}