package com.example.squadmaker.di

import android.content.Context
import androidx.room.Room
import com.example.squadmaker.model.localdatasouce.LocalDataSource
import com.example.squadmaker.model.localdatasouce.LocalDataSourceImpl
import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
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