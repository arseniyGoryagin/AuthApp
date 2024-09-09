package com.authapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("token_store")


class DataStore(private val context: Context){


    private val TOKEN_NAME = stringPreferencesKey("token")

    suspend fun saveToken(token : String){
        context.dataStore.edit { pref ->
            pref[TOKEN_NAME] = token

        }
    }

    suspend fun removeToken(){
        context.dataStore.edit { pref ->
            pref[TOKEN_NAME] = ""

        }
    }

    fun getToken() : Flow<String?> {
        return context.dataStore.data.map { pref ->
            pref[TOKEN_NAME]
        }
    }


}