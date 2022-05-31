package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.core_db.domain.CharacterModel

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
    val createDate: String,
    val image: String
) {
    fun toCharacter(): CharacterModel = CharacterModel(
        id = id,
        name = name,
        gender = gender,
        status = status,
        species = species,
        createDate = createDate,
        image = image
    )
}

