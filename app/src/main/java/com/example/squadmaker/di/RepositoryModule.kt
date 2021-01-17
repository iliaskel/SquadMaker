package com.example.squadmaker.di

import com.example.squadmaker.model.repository.*
import com.example.squadmaker.model.repository.utils.SquadRepository
import com.example.squadmaker.model.repository.utils.SquadRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.annotation.Signed
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class RepositoryModule {
//    @ActivityRetainedScoped
//    @Binds
//    abstract fun provideRepositoryModule(
//        repositoryImpl: RepositoryImpl
//    ): Repository

    @Singleton
    @Binds
    abstract fun provideCharactersRepositoryModule(
        repositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository

    @Singleton
    @Binds
    abstract fun provideSquadRepositoryModule(
        repositoryImpl: SquadRepositoryImpl
    ): SquadRepository

    @Singleton
    @Binds
    abstract fun provideComicsRepositoryModule(
        comicsRepositoryImpl: ComicsRepositoryImpl
    ): ComicsRepository
}