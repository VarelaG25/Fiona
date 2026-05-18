package com.refuge.presentation.session

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PetSessionViewModel @Inject constructor () : ViewModel() {

    private val _selectedPetId = MutableStateFlow<Int?>(null)
    val selectedPetId: StateFlow<Int?> = _selectedPetId.asStateFlow()

    fun setPet(id: Int) {
        _selectedPetId.value = id
    }

    fun clear() {
        _selectedPetId.value = null
    }
}