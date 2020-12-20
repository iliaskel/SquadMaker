package com.example.squadmaker.model.localdatasouce

import androidx.lifecycle.LiveData
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity

interface SquadLocalDataSource {

    suspend fun insertSquadMember(squadEntity: SquadEntity)

    suspend fun deleteSquadMember(characterId: Int)

    suspend fun getSquadListForCharacterId(characterId: Int): List<SquadEntity>

    fun getSquad(): LiveData<List<SquadEntity>>

}