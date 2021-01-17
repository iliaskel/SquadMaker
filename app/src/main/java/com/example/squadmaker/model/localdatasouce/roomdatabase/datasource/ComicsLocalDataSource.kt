package com.example.squadmaker.model.localdatasouce.roomdatabase.datasource

import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ComicsLocalDataSource {

    suspend fun replaceComicsList(comicsEntityList: List<ComicsEntity>)

    suspend fun deleteComics()

    fun getComicsListFlow(): Flow<List<ComicsEntity>>

}

class ComicsLocalDataSourceImpl @Inject constructor(squadDatabase: SquadDatabase) :
    ComicsLocalDataSource {

    private val comicsDao = squadDatabase.comicsDao()

    override suspend fun replaceComicsList(comicsEntityList: List<ComicsEntity>) {
        comicsDao.replaceComicsList(comicsEntityList)
    }

    override suspend fun deleteComics() {
        comicsDao.deleteAllComics()
    }

    override fun getComicsListFlow(): Flow<List<ComicsEntity>> {
        return comicsDao.getAllComicsListFlow()
    }

}