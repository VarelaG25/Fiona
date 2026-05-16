package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.refuge.domain.usecase.RegisterUserUseCase

class RegisterViewModelFactory(
    private val useCase: RegisterUserUseCase
) : androidx.lifecycle.ViewModelProvider.Factory {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(useCase) as T
    }
}