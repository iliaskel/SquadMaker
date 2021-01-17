package com.example.squadmaker.model.repository.utils

import com.example.squadmaker.model.localdatasouce.roomdatabase.datasource.SquadLocalDataSource
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import com.example.squadmaker.model.remotedatasource.RemoteDataSource
import com.example.squadmaker.model.repository.mapper.Mapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface SquadRepository {

    suspend fun updateSquadEntry(isSquadMember: Boolean)

    fun getSquadListFlow(): Flow<List<SquadEntity>>
}

class SquadRepositoryImpl @Inject constructor(
    private val squadLocalDataSource: SquadLocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: Mapper
) : SquadRepository {


    override suspend fun updateSquadEntry(isSquadMember: Boolean) {

    }

    override fun getSquadListFlow(): Flow<List<SquadEntity>> {
        return squadLocalDataSource.getSquadListFlow()
    }

}