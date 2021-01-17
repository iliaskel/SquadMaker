package com.example.squadmaker.model.repository

import com.example.squadmaker.model.localdatasouce.roomdatabase.datasource.ComicsLocalDataSource
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.repository.mapper.Mapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ComicsRepository {

    suspend fun fetchAndSaveComicsByCharacterId(characterId: Int)

    fun getComicsListFlow(): Flow<List<ComicsEntity>>
}

class ComicsRepositoryImpl @Inject constructor(
    private val comicsLocalDataSource: ComicsLocalDataSource,
    private val mapper: Mapper,
    private val remoteDataSource: RemoteDataSource
) : ComicsRepository {

    override suspend fun fetchAndSaveComicsByCharacterId(characterId: Int) {
        val comics = remoteDataSource.fetchComicsByCharacterId(characterId)
        val mappedComics = mapper.mapToComicEntity(comics)
        comicsLocalDataSource.replaceComicsList(mappedComics)
    }

    override fun getComicsListFlow(): Flow<List<ComicsEntity>> {
        return comicsLocalDataSource.getComicsListFlow()
    }

}