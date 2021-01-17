package com.example.squadmaker.di

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.responses.characters.CharacterResultsDTO
import com.example.squadmaker.model.remotedatasource.responses.comics.ComicsResponseDTO
import com.example.squadmaker.model.repository.mapper.Mapper
import com.example.squadmaker.model.repository.mapper.MapperImpl
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.DTOToEntityMapper
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.character.CharacterMapper
import com.example.squadmaker.model.repository.mapper.dtotoentitymapper.comics.ComicsMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.CharacterEntityToSquadEntityMapper
import com.example.squadmaker.model.repository.mapper.entitytoentitymapper.EntityToEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class MapperModule {
    @Singleton
    @Binds
    abstract fun bindMapperModule(
        mapperImpl: MapperImpl
    ): Mapper
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class CharacterMapperModule {
    @Singleton
    @Binds
    abstract fun bindCharacterMapperModule(
        characterMapper: CharacterMapper
    ): DTOToEntityMapper<CharacterResultsDTO, CharacterEntity>
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class SquadMapper {
    @Singleton
    @Binds
    abstract fun bindCharacterToSquadMapperModule(
        characterEntityToSquadEntityMapper: CharacterEntityToSquadEntityMapper
    ): EntityToEntityMapper<CharacterEntity, SquadEntity>
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class ComicsMapperModule {
    @Singleton
    @Binds
    abstract fun bindComicsMapperModule(
        comicsMapper: ComicsMapper
    ): DTOToEntityMapper<ComicsResponseDTO, List<ComicsEntity>>
}