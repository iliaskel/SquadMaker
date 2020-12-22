package com.example.squadmaker.model.localdatasouce

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import kotlinx.coroutines.flow.Flow

interface ComicsLocalDataSource {

    suspend fun replaceComicsList(comicsEntityList: List<ComicsEntity>)

    suspend fun deleteComics()

    fun getComics(): Flow<List<ComicsEntity>>

}