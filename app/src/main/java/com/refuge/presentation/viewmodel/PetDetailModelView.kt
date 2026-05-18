package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.data.local.entity.PetEntity
import com.refuge.domain.usecase.PetAdopterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val useCase: PetAdopterUseCase
) : ViewModel() {

    private val _pet = MutableStateFlow<PetEntity?>(null)
    val pet: StateFlow<PetEntity?> = _pet

    fun loadPet(id: Int) {
        viewModelScope.launch {
            _pet.value = useCase.getPetById(id)
        }
    }

    fun adoptPet(idUsuario: Int, idPet: Int) {
        viewModelScope.launch {
            useCase.adoptPet(idUsuario, idPet)
        }
    }
}