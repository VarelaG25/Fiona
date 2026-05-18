package com.refuge.domain.usecase

import com.refuge.data.dummy.PetsDummy
import com.refuge.data.repository.PetRepository
import com.refuge.data.local.entity.PetEntity
import javax.inject.Inject

class PetAdopterUseCase @Inject constructor(
    private val repository: PetRepository
) {

    // =========================
    // PETS
    // =========================

    suspend fun getAllPets(): List<PetEntity> {
        return repository.getAllPets()
    }

    suspend fun searchPetsByName(name: String): List<PetEntity> {
        return repository.searchPetsByName(name)
    }

    suspend fun insertPet(pet: PetEntity) {
        repository.insertPet(pet)
    }

    suspend fun getPetById(id: Int): PetEntity? {
        return repository.getPetById(id)
    }

    // =========================
    // ADOPTION
    // =========================

    suspend fun adoptPet(idUsuario: Int, idPet: Int) {
        repository.adoptPet(idUsuario, idPet)
    }

    suspend fun seedPetsIfNeeded() {
        repository.seedPetsIfEmpty(PetsDummy.pets)
    }
}