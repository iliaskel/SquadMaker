package com.example.squadmaker.di

import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.remotedatasource.RemoteDataSourceImpl
import com.example.squadmaker.model.remotedatasource.apis.*
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

private const val MARVEL_API_BASE_URL: String = "https://gateway.marvel.com:443/"

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
class CharactersApiModule {

    @Singleton
    @Provides
    fun provideCharactersApiModule(): CharactersApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(CharactersApi::class.java)
    }
}

@InstallIn(ApplicationComponent::class)
@Module
class ComicsApiModule {
    @Singleton
    @Provides
    fun provideComicsApiModule(): ComicsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(ComicsApi::class.java)
    }
}

@InstallIn(ApplicationComponent::class)
@Module
class CreatorsApiModule {
    @Singleton
    @Provides
    fun provideCreatorsApiModule(): CreatorsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(CreatorsApi::class.java)
    }
}

@InstallIn(ApplicationComponent::class)
@Module
class EventsApiModule {
    @Singleton
    @Provides
    fun provideEventsApiModule(): EventsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(EventsApi::class.java)
    }
}

@InstallIn(ApplicationComponent::class)
@Module
class StoriesApiModule {
    @Singleton
    @Provides
    fun provideStoriesApiModule(): StoriesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(StoriesApi::class.java)
    }
}

@InstallIn(ApplicationComponent::class)
@Module
class SeriessApiModule {
    @Singleton
    @Provides
    fun provideSeriesApiModule(): SeriesApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(MARVEL_API_BASE_URL)
            .client(getHttpClientInterceptor())
            .build()
            .create(SeriesApi::class.java)
    }
}


private fun getHttpClientInterceptor(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val httpClient = OkHttpClient.Builder()

    return httpClient.addInterceptor(logging).build()
}