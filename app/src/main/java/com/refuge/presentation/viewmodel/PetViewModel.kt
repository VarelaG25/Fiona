package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.domain.usecase.PetAdopterUseCase
import com.refuge.presentation.state.PetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val useCase: PetAdopterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PetState())
    val state: StateFlow<PetState> = _state

    init {
        loadPets()
    }

    fun loadPets() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val pets = useCase.getAllPets()

                _state.update {
                    it.copy(
                        isLoading = false,
                        pets = pets
                    )
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun searchByName(name: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, searchQuery = name) }

            val result = useCase.searchPetsByName(name)

            _state.update {
                it.copy(
                    isLoading = false,
                    pets = result
                )
            }
        }
    }

    fun adoptPet(idUsuario: Int, idPet: Int) {
        viewModelScope.launch {
            try {
                useCase.adoptPet(idUsuario, idPet)
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e.message)
                }
            }
        }
    }
}