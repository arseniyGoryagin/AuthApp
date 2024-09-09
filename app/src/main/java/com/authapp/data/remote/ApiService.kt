package com.authapp.data.remote

import com.authapp.auth.domain.model.requests.LoginRequest
import com.authapp.auth.domain.model.requests.RegisterRequest
import com.authapp.auth.domain.model.responses.LoginResponse
import com.authapp.auth.domain.model.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body loginReq : LoginRequest) : LoginResponse

    @POST("register")
    suspend fun register(@Body registerReq : RegisterRequest) : Response<Unit>

    @GET("authenticate")
    suspend fun authenticate(@Header("Authorization") token : String) : Response<Unit>

        @GET("user")
        suspend fun getUserData(
            @Header("Authorization") token : String
        ) : UserResponse

}