package com.test.core_db.core_db_api.di

import com.test.core_db.core_db_api.data.DbClientApi

interface CoreDbApi {
    fun dbClientApi(): DbClientApi
}
