package com.authapp.auth.domain.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email : String,
    val password : String,
    val phone : String,
    val name : String
)