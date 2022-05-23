package com.test.core_network.core_network_impl.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.core_network.core_network_api.data.CharacterResponse
import com.test.core_network.core_network_api.data.RetrofitClientApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(): RetrofitClientApi {
    companion object RetrofitClient {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        private var retrofit: Retrofit? = null

        private var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        private fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }
            return retrofit!!
        }

        val rickAndMortyService: RetrofitService
            get() = getClient().create(RetrofitService::class.java)
    }

    override suspend fun getCharacters(): List<CharacterResponse> {
        return rickAndMortyService.getCharactersAsync().await().results
    }
}
