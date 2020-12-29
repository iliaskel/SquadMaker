package com.example.squadmaker.di

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.repository.mapper.Mapper
import com.example.squadmaker.model.repository.mapper.MapperImpl
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.CharacterMapper
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.ComicsMapper
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DetailedCharacterMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.DetailedCharacterEntityToSquadEntityMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.EntityToEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
abstract class MapperModule {
    @ActivityScoped
    @Binds
    abstract fun bindMapperModule(
        mapperImpl: MapperImpl
    ): Mapper
}

@InstallIn(ActivityComponent::class)
@Module
abstract class CharacterMapperModule {
    @ActivityScoped
    @Binds
    abstract fun bindCharacterMapperModule(
        characterMapper: CharacterMapper
    ): DTOToEntityMapper<CharacterResultsDTO, CharacterEntity>
}

@InstallIn(ActivityComponent::class)
@Module
abstract class DetailedCharacterMapperModule {
    @ActivityScoped
    @Binds
    abstract fun bindDetailedCharacterMapperModule(
        detailedCharacterMapper: DetailedCharacterMapper
    ): DTOToEntityMapper<CharacterResultsDTO, DetailedCharacterEntity>
}

@InstallIn(ActivityComponent::class)
@Module
abstract class DetailedCharacterToSquadMapperModule {
    @ActivityScoped
    @Binds
    abstract fun bindDetailedCharacterToSquadMapperModule(
        detailedCharacterEntityToSquadEntityMapper: DetailedCharacterEntityToSquadEntityMapper
    ): EntityToEntityMapper<DetailedCharacterEntity, SquadEntity>
}

@InstallIn(ActivityComponent::class)
@Module
abstract class ComicsMapperModule {
    @ActivityScoped
    @Binds
    abstract fun bindComicsMapperModule(
        comicsMapper: ComicsMapper
    ): DTOToEntityMapper<ComicsResponseDTO, List<ComicsEntity>>
}