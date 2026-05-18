package com.refuge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refuge.data.local.entity.PetEntity
import com.refuge.domain.usecase.PetAdopterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdoptedPetsViewModel @Inject constructor(
    private val useCase: PetAdopterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<List<PetEntity>>(emptyList())
    val state: StateFlow<List<PetEntity>> = _state

    fun loadAdoptedPets(userId: Int) {
        viewModelScope.launch {
            _state.value = useCase.getAdoptedPets(userId)
        }
    }
}