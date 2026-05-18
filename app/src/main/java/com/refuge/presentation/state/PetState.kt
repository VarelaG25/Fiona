package com.refuge.presentation.state

import com.refuge.data.local.entity.PetEntity

data class PetState(
    val isLoading: Boolean = false,
    val pets: List<PetEntity> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)