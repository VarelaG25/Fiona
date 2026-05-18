package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.domain.usecase.PetAdopterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: PetAdopterUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            useCase.seedPetsIfNeeded()
            loadPets()
        }
    }

    private fun loadPets() {
        // llamar getAllPets y actualizar UI state
    }
}