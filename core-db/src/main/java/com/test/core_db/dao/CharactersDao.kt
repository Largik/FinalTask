package com.test.core_db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.core_db.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("SELECT * from characters WHERE id = :Id")
    fun getCharacter(Id: Int): Flow<CharacterEntity>

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :searchString || '%'")
    fun getCharacters(searchString: String): PagingSource<Int, CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(characters: List<CharacterEntity>)
}
