package com.example.squadmaker.model.localdatasouce.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.CharactersDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.ComicsDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.dao.SquadDao
import com.example.squadmaker.model.localdatasouce.roomdatabase.entity.*
import com.example.squadmaker.model.localdatasouce.roomdatabase.utils.ComicsPriceListConverter

/**
 * An abstract [RoomDatabase] representation and the corresponding DAOs
 */
@Database(
    entities = [
        SquadEntity::class,
        CharacterEntity::class,
        ComicsEntity::class,
        SeriesEntity::class,
        EventEntity::class,
        StoryEntity::class,
        CreatorEntity::class,
    ],
    version = 5,
    exportSchema = false
)
@TypeConverters(ComicsPriceListConverter::class)
abstract class SquadDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun squadDao(): SquadDao
    abstract fun comicsDao(): ComicsDao
}
