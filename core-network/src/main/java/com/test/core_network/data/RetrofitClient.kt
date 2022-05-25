package com.test.core_network.data

import javax.inject.Inject

class RetrofitClient @Inject constructor(private val rickAndMortyService: RetrofitService){
    suspend fun getCharacters(): List<CharacterResponse> {
        return rickAndMortyService.getCharactersAsync().await().results
    }
}
