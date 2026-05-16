package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.domain.model.Usuario
import com.refuge.domain.usecase.RegisterUserUseCase
import com.refuge.presentation.state.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@dagger.hilt.android.lifecycle.HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()

    fun onFullNameChange(value: String) {
        _state.value = _state.value.copy(fullName = value)
    }

    fun onEmailChange(value: String) {
        _state.value = _state.value.copy(email = value)
    }

    fun onPhoneChange(value: String) {
        _state.value = _state.value.copy(phone = value)
    }

    fun onPasswordChange(value: String) {
        _state.value = _state.value.copy(password = value)
    }

    fun registerUser() {
        viewModelScope.launch {
            try {
                val user = Usuario(
                    fullName = state.value.fullName,
                    email = state.value.email,
                    phone = state.value.phone,
                    password = state.value.password
                )

                registerUserUseCase(user)

                _state.value = _state.value.copy(
                    message = "Usuario registrado correctamente"
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    message = "Error: ${e.message}"
                )
            }
        }
    }
}