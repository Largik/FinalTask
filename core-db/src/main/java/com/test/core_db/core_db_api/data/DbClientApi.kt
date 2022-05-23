package com.test.core_db.core_db_api.data

import android.content.Context
import com.test.core_db.core_db_api.data.repositories.CharactersRepository

interface DbClientApi {
    val charactersRepository: CharactersRepository
    fun init(context: Context)
}
