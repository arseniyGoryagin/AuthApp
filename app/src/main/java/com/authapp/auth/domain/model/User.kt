package com.authapp.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : String,
    val name : String,
    val email : String,
    val password : String,
    val phone : String,
    val salt : String
)