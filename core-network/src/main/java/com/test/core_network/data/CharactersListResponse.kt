package com.test.core_network.data

import com.squareup.moshi.Json

data class CharactersListResponse(
    @Json(name = "results")
    val results: List<CharacterResponse>
)
