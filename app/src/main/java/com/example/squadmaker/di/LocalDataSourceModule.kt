package com.example.squadmaker.di

import android.content.Context
import androidx.room.Room
import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
import com.example.squadmaker.model.localdatasouce.roomdatabase.datasource.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class LocalDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindCharactersLocalSourceModule(
        charactersLocalDataSourceImpl: CharactersLocalDataSourceImpl
    ): CharactersLocalDataSource
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class SquadLocalDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindSquadLocalSourceModule(
        squadLocalDataSource: SquadLocalDataSourceImpl
    ): SquadLocalDataSource
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class ComicsLocalDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindComicsLocalDataSourceModule(
        comicsLocalDataSourceImpl: ComicsLocalDataSourceImpl
    ): ComicsLocalDataSource
}

@InstallIn(ApplicationComponent::class)
@Module
class RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideRoomDatabaseModule(@ApplicationContext applicationContext: Context): SquadDatabase {
        return Room.databaseBuilder(applicationContext, SquadDatabase::class.java, "marvel_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}