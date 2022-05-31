package com.test.core_network.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") query: String
    ): CharactersResponse
}