package com.refuge.domain.model

data class Usuario(
    val id: Int = 0,
    val fullName: String,
    val email: String,
    val phone: String,
    val password: String
)