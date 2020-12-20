package com.example.squadmaker.di

import android.content.Context
import androidx.room.Room
import com.example.squadmaker.model.localdatasouce.LocalDataSource
import com.example.squadmaker.model.localdatasouce.LocalDataSourceImpl
import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.remotedatasource.RemoteDataSourceImpl
import com.example.squadmaker.model.remotedatasource.retrofit.api.MarvelApiService
import com.example.squadmaker.repository.Repository
import com.example.squadmaker.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {

    @ActivityScoped
    @Binds
    abstract fun bindRepositoryModule(
        repositoryImpl: RepositoryImpl
    ): Repository
}

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

@InstallIn(ActivityComponent::class)
@Module
abstract class LocalDataSource {
    @Singleton
    @Binds
    abstract fun bindLocalSourceModule(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}

@InstallIn(ApplicationComponent::class)
@Module
class RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabaseModule(@ApplicationContext applicationContext: Context): SquadDatabase {
        return Room.databaseBuilder(applicationContext, SquadDatabase::class.java, "marvel_db")
            .build()
    }
}

private fun getHttpClientInterceptor(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val httpClient = OkHttpClient.Builder()

    return httpClient.addInterceptor(logging).build()
}