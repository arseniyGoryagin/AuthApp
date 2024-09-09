package com.authapp.di

import android.content.Context
import com.authapp.data.remote.ApiService
import com.authapp.data.repository.Repository
import com.authapp.auth.domain.repository.AuthRepository
import com.authapp.data.local.DataStore
import com.authapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import javax.inject.Singleton
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType


@Module
@InstallIn(SingletonComponent::class)
class AppModule(){


    @Provides
    @Singleton
    fun providesRetroFit() : ApiService {


        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                Json{ignoreUnknownKeys = false}.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(api : ApiService, dataStore: DataStore) : AuthRepository{
        return Repository(api,dataStore )
    }


    @Provides
    @Singleton
    fun providesDataStore(@ApplicationContext context: Context) : DataStore{
        return DataStore(context)
    }


}