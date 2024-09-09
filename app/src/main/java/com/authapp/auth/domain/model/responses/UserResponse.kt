package com.authapp.auth.domain.model.responses

import com.authapp.auth.domain.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse (
    val userData : User
)