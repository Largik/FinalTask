package com.test.core_db.core_db_impl.data

import android.content.Context
import androidx.room.Room
import com.test.core_db.core_db_api.data.DbClientApi
import com.test.core_db.core_db_impl.data.repositories.CharactersRepositoryImpl
import javax.inject.Inject

class DbClientImpl @Inject constructor(): DbClientApi {
    private lateinit var appContext: Context
    private lateinit var charactersDB: AppDatabase

    fun getDatabase(context: Context): AppDatabase {
        synchronized(AppDatabase::class.java) {
            if (!::charactersDB.isInitialized) {
                charactersDB = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "CharactersFromRickAndMortyDB"
                ).build()
            }
        }
        return charactersDB
    }

    override val charactersRepository: CharactersRepositoryImpl by lazy{
        CharactersRepositoryImpl(getDatabase(appContext).getCharacterDao())
    }

    override fun init(context: Context) {
        appContext = context
    }
}