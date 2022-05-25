package com.test.core_network.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RetrofitService {
    @GET("character")
    suspend fun getCharactersAsync(): Deferred<CharactersListResponse>
}
