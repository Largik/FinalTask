package com.test.core_db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.core_db.models.CharacterModel

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
    val created: String,
    val image: String?
) {
    fun toCharacter(): CharacterModel = CharacterModel(
        id = id,
        name = name,
        gender = gender,
        status = status,
        species = species,
        created = created,
        image = image
    )
}

