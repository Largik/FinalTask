package com.test.core_db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.core_db.entities.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterEntity?

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterEntity>)
}
