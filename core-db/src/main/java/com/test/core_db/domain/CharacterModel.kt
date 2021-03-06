package com.test.core_db.domain

data class CharacterModel(
    val id: Int,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
    val createDate: String,
    val image: String
)
