package com.test.core_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.core_db.dao.CharactersDao
import com.test.core_db.dao.RemoteKeyDao
import com.test.core_db.entities.CharacterEntity
import com.test.core_db.entities.RemoteKey

@Database(entities = [CharacterEntity::class, RemoteKey::class], version = 1)
abstract class RAMDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}

private lateinit var INSTANCE: RAMDatabase

fun getDatabase(context: Context): RAMDatabase {
    synchronized(RAMDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                RAMDatabase::class.java,
                "RickAndMortyCharactersDB"
            ).build()
        }
    }
    return INSTANCE
}