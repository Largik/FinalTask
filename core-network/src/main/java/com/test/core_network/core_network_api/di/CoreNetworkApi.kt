package com.test.core_network.core_network_api.di

import com.test.core_network.core_network_api.data.RetrofitClientApi

interface CoreNetworkApi {
    fun retrofitClientApi(): RetrofitClientApi
}
