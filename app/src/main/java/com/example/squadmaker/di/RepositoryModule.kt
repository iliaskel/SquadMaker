package com.example.squadmaker.di

import com.example.squadmaker.model.repository.Repository
import com.example.squadmaker.model.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {
    @ActivityScoped
    @Binds
    abstract fun bindRepositoryModule(
        repositoryImpl: RepositoryImpl
    ): Repository
}