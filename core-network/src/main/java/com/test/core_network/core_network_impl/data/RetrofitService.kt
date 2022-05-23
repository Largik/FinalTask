package com.test.core_network.core_network_impl.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitService {
    @GET("character")
    @Headers("Content-Type: application/json")
    fun getCharactersAsync(): Deferred<CharactersListResponse>
}
