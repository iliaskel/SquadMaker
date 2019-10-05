package com.example.squadmaker.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.squadmaker.model.database.dao.CharactersDao
import com.example.squadmaker.model.database.dao.DetailedCharacterDao
import com.example.squadmaker.model.database.dao.SquadDao
import com.example.squadmaker.model.database.entity.CharacterEntity
import com.example.squadmaker.model.database.entity.DetailedCharacterEntity
import com.example.squadmaker.model.database.entity.SquadEntity

@Database(
    entities = [SquadEntity::class, DetailedCharacterEntity::class, CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SquadDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun squadDao(): SquadDao
    abstract fun detailedCharacterDao(): DetailedCharacterDao

    companion object {

        @Volatile
        private var instance: SquadDatabase? = null

        fun getInstance(context: Context): SquadDatabase {
            if (instance != null) {
                return instance as SquadDatabase
            }
            synchronized(this) {
                val _instance =
                    Room
                        .databaseBuilder(
                            context.applicationContext,
                            SquadDatabase::class.java,
                            "marvel_db"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                instance = _instance
                return _instance
            }
        }
    }
}
