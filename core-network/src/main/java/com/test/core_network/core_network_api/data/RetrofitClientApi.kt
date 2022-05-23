package com.test.core_network.core_network_api.data

interface RetrofitClientApi {
    suspend fun getCharacters(): List<CharacterResponse>
}
