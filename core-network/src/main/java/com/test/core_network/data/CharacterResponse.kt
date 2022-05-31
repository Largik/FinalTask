package com.test.core_network.data

import com.squareup.moshi.Json
import java.util.*

data class CharacterResponse(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "gender")
    val gender: String,

    @Json(name = "status")
    val status: String,

    @Json(name = "species")
    val species: String,

    @Json(name = "created")
    val createDate: Date,

    @Json(name = "image")
    val image: String
)