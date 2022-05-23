package com.test.core_db.core_db_impl.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.core_db.core_db_impl.data.entities.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterEntity?

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterEntity>)
}
