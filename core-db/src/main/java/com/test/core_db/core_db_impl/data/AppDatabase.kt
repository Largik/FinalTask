package com.test.core_db.core_db_impl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.core_db.core_db_impl.data.dao.CharacterDao
import com.test.core_db.core_db_impl.data.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}

