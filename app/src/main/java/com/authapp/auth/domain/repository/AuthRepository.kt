package com.authapp.auth.domain.repository
import arrow.core.Either
import com.authapp.auth.domain.model.responses.LoginResponse
import com.authapp.auth.domain.model.responses.UserResponse
import retrofit2.Response


interface AuthRepository {

    suspend fun login(email : String, password : String) : Either<Exception, LoginResponse>

    suspend fun register(email : String, password : String, phone : String, name: String)  : Response<Unit>

    suspend fun getUserData() : Either<Exception, UserResponse>

    suspend fun authenticate() : Response<Unit>

    suspend fun saveJWT(token : String)

    suspend fun logOut()


}