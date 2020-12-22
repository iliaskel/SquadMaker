package com.example.squadmaker.model.localdatasouce

import com.example.squadmaker.model.localdatasouce.roomdatabase.SquadDatabase
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl
@Inject
constructor(
    squadDatabase: SquadDatabase
) : LocalDataSource {

    private val charactersDao = squadDatabase.charactersDao()
    private val squadDao = squadDatabase.squadDao()
    private val detailedCharacterDao = squadDatabase.detailedCharacterDao()
    private val comicsDao = squadDatabase.comicsDao()

    override suspend fun replaceCharacterList(characterEntityList: List<CharacterEntity>) {
        charactersDao.replaceCharacterList(characterEntityList)
    }

    override fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return charactersDao.getAllCharacters()
    }

    override suspend fun replaceComicsList(comicsEntityList: List<ComicsEntity>) {
        comicsDao.replaceComics(comicsEntityList)
    }

    override suspend fun deleteComics() {
        comicsDao.deleteComics()
    }

    override fun getComics(): Flow<List<ComicsEntity>> {
        return comicsDao.getComics()
    }

    override suspend fun replaceDetailedCharacter(detailedCharacterEntity: DetailedCharacterEntity) {
        detailedCharacterDao.replaceDetailedCharacter(detailedCharacterEntity)
    }

    override suspend fun deleteDetailedCharacter() {
        detailedCharacterDao.deleteDetailedCharacters()
    }

    override suspend fun getDetailedCharacterEntity(): DetailedCharacterEntity {
        return detailedCharacterDao.getDetailedCharacterEntity()
    }

    override fun getDetailedCharacter(): Flow<DetailedCharacterEntity?> {
        return detailedCharacterDao.getDetailedCharacter()
    }

    override suspend fun insertSquadMember(squadEntity: SquadEntity) {
        squadDao.insertSquadMember(squadEntity)
    }

    override suspend fun deleteSquadMember(characterId: Int) {
        squadDao.deleteSquadMember(characterId)
    }

    override suspend fun getSquadListForCharacterId(characterId: Int): List<SquadEntity> {
        return squadDao.getSquadListForCharacterId(characterId)
    }

    override fun getSquad(): Flow<List<SquadEntity>> {
        return squadDao.getSquad()
    }

}