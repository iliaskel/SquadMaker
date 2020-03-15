package com.example.squadmaker.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.squadmaker.model.database.dao.CharactersDao
import com.example.squadmaker.model.database.dao.ComicsDao
import com.example.squadmaker.model.database.dao.DetailedCharacterDao
import com.example.squadmaker.model.database.dao.SquadDao
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.ComicsEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity

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
