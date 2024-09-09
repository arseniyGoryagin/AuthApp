package com.authapp.auth.domain.model.responses

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token : String
)