package com.example.squadmaker.model.localdatasouce

import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import kotlinx.coroutines.flow.Flow

interface SquadLocalDataSource {

    suspend fun insertSquadMember(squadEntity: SquadEntity)

    suspend fun deleteSquadMember(characterId: Int)

    suspend fun getSquadListForCharacterId(characterId: Int): List<SquadEntity>

    fun getSquad(): Flow<List<SquadEntity>>

}