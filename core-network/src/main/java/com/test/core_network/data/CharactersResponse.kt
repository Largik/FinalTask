package com.test.core_network.data

import android.annotation.SuppressLint
import com.squareup.moshi.JsonClass
import com.test.core_db.entities.CharacterEntity
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class CharactersResponse(
    val info: Info,
    val results: List<CharacterResponse>
)

@SuppressLint("SimpleDateFormat")
fun List<CharacterResponse>.toCharacterEntity(): List<CharacterEntity> {
    return map { characterResponse ->
        CharacterEntity(
            id = characterResponse.id,
            name = characterResponse.name,
            gender = characterResponse.gender,
            status = characterResponse.status,
            species = characterResponse.species,
            createDate = SimpleDateFormat("MM-dd-yyyy")
                .format(characterResponse.createDate)
                .toString(),
            image = characterResponse.image
        )
    }
}

