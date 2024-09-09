package com.authapp.auth.domain.model.requests

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    val email : String,
    val password : String
)