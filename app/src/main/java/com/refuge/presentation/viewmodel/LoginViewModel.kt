package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.data.repository.UsuarioRepository
import com.refuge.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UsuarioRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChange(password: String) {
        _state.update { it.copy(password = password, errorMessage = null) }
    }

    fun loginUser() {
        val email = _state.value.email.trim()
        val password = _state.value.password

        if (email.isEmpty() || password.isEmpty()) {
            _state.update { it.copy(errorMessage = "Por favor, llena todos los campos.") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            // Consultamos la base de datos local a través del repositorio
            val user = repository.getUserByEmail(email)

            if (user == null) {
                _state.update { it.copy(isLoading = false, errorMessage = "El correo electrónico no está registrado.") }
            } else if (user.password != password) {
                _state.update { it.copy(isLoading = false, errorMessage = "Contraseña incorrecta.") }
            } else {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoginSuccess = true,
                        errorMessage = null
                    )
                }
            }
        }
    }

    fun resetSuccessState() {
        _state.update { it.copy(isLoginSuccess = false) }
    }
}
