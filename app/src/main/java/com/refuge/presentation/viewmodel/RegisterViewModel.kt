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

        val fullName = state.value.fullName.trim()
        val email = state.value.email.trim()
        val phone = state.value.phone.trim()
        val password = state.value.password.trim()

        if (fullName.isBlank() ||
            email.isBlank() ||
            phone.isBlank() ||
            password.isBlank()
        ) {
            _state.value = _state.value.copy(
                isLoading = false,
                isSuccess = false,
                message = "Please complete all fields"
            )
            return
        }

        if (password.length < 6) {
            _state.value = _state.value.copy(
                isLoading = false,
                isSuccess = false,
                message = "Invalid data"
            )
            return
        }

        _state.value = _state.value.copy(
            isLoading = true,
            message = null,
            isSuccess = false
        )

        viewModelScope.launch {
            try {

                val user = Usuario(
                    fullName = fullName,
                    email = email,
                    phone = phone,
                    password = password
                )

                registerUserUseCase(user)

                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = true,
                    message = "Success"
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isSuccess = false,
                    message = "Unable to complete the request"
                )
            }
        }
    }

    fun resetState() {
        _state.value = _state.value.copy(
            message = null,
            isSuccess = false
        )
    }
}