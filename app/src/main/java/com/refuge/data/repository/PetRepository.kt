package com.refuge.data.repository

import com.refuge.data.local.dao.PetDao
import com.refuge.data.local.entity.AdopterEntity
import com.refuge.data.local.entity.PetEntity
import javax.inject.Inject

class PetRepository @Inject constructor(
    private val petDao: PetDao
) {

    // =========================
    // PETS
    // =========================

    suspend fun insertPet(pet: PetEntity) {
        petDao.insertPet(pet)
    }


    suspend fun getAllPets(): List<PetEntity> {
        return petDao.getAllPets()
    }

    suspend fun searchPetsByName(name: String): List<PetEntity> {
        return petDao.getPetsByName(name)
    }

    suspend fun getPetById(id: Int): PetEntity? {
        return petDao.getPetById(id)
    }

    // =========================
    // ADOPTION
    // =========================

    suspend fun adoptPet(idUsuario: Int, idPet: Int) {
        petDao.insertAdoption(
            AdopterEntity(
                id = 0,
                idUsuario = idUsuario,
                idPet = idPet
            )
        )
    }

    suspend fun seedPetsIfEmpty(dummies: List<PetEntity>) {
        if (petDao.getAllPets().isEmpty()) {
            petDao.insertAll(dummies)
        }
    }

    suspend fun getAdoptedPets(userId: Int): List<PetEntity> {
        return petDao.getAdoptedPets(userId)
    }
}