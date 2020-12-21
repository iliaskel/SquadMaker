package com.example.squadmaker.di

import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.remotedatasource.RemoteDataSourceImpl
import com.example.squadmaker.model.remotedatasource.retrofit.api.MarvelApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RemoteSourceModule {
    @Singleton
    @Binds
    abstract fun bindRemoteSourceModule(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}

@InstallIn(ApplicationComponent::class)
@Module
class RetrofitModule {

    private val MARVEL_API_BASE_URL: String = "https://gateway.marvel.com:443/"

    @Singleton
    @Provides
    fun provideRetrofitModule(): MarvelApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(MarvelApiService::class.java)
    }
}

private fun getHttpClientInterceptor(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val httpClient = OkHttpClient.Builder()

    return httpClient.addInterceptor(logging).build()
}