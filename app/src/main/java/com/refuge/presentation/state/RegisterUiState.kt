package com.refuge.presentation.state

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val message: String? = null,
    val loading: Boolean = false
)