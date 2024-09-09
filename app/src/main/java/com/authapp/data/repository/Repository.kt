package com.authapp.data.repository

import arrow.core.Either
import com.authapp.data.remote.ApiService
import com.authapp.auth.domain.model.requests.LoginRequest
import com.authapp.auth.domain.model.requests.RegisterRequest
import com.authapp.auth.domain.model.responses.LoginResponse
import com.authapp.auth.domain.model.responses.UserResponse
import com.authapp.auth.domain.repository.AuthRepository
import com.authapp.data.local.DataStore
import kotlinx.coroutines.flow.first
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService, private val dataStore: DataStore) : AuthRepository {



    override suspend fun login(email: String, password: String): Either<Exception, LoginResponse> {
            return Either.catch {
                apiService.login(LoginRequest(email, password))
            } .mapLeft { it as Exception }
    }

    override suspend fun register(email: String, password: String, phone: String, name: String) : Response<Unit>{
        return apiService.register(RegisterRequest(email, password, phone, name))
    }

    override suspend fun getUserData(): Either<Exception, UserResponse> {

        val token = dataStore.getToken().first()

        println("Token === " + token)

        return Either.catch {
            apiService.getUserData("Bearer $token")
        } .mapLeft { it as Exception }
    }


    override suspend fun authenticate() : Response<Unit> {

        val token = dataStore.getToken().first()

        println("Token === " + token)

        return apiService.authenticate("Bearer $token")
    }

    override suspend fun saveJWT(token : String) {
        dataStore.saveToken(token)
    }

    override suspend fun logOut() {
        dataStore.removeToken()
    }


}