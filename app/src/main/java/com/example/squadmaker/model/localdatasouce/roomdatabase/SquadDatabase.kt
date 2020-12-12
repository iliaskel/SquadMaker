package com.example.squadmaker.model.localdatasouce.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.CharactersDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.ComicsDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.DetailedCharacterDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.SquadDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.CharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.ComicsEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.DetailedCharacterEntity
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.SquadEntity

/**
 * An abstract [RoomDatabase] representation and the corresponding DAOs
 */
@Database(
    entities = [
        SquadEntity::class,
        DetailedCharacterEntity::class,
        CharacterEntity::class,
        ComicsEntity::class],
    version = 4,
    exportSchema = false
)
abstract class SquadDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun squadDao(): SquadDao
    abstract fun detailedCharacterDao(): DetailedCharacterDao
    abstract fun comicsDao(): ComicsDao
}
