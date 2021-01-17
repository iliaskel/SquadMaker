package com.example.squadmaker.model.localdatasouce.roomdatabase.datasource

import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SquadLocalDataSource {

    suspend fun insertSquadMember(squadEntity: SquadEntity)

    suspend fun deleteSquadMember(characterId: Int)

    suspend fun getSquadListForCharacterId(characterId: Int): List<SquadEntity>

    fun getSquadListFlow(): Flow<List<SquadEntity>>

}

class SquadLocalDataSourceImpl @Inject constructor(
    squadDatabase: SquadDatabase
) : SquadLocalDataSource {

    private val squadDao = squadDatabase.squadDao()

    override suspend fun insertSquadMember(squadEntity: SquadEntity) {
        squadDao.insertSquadMember(squadEntity)
    }

    override suspend fun deleteSquadMember(characterId: Int) {
        squadDao.deleteSquadMember(characterId)
    }

    override suspend fun getSquadListForCharacterId(characterId: Int): List<SquadEntity> {
        return squadDao.getSquadListForCharacterId(characterId)
    }

    override fun getSquadListFlow(): Flow<List<SquadEntity>> {
        return squadDao.getSquad()
    }
}