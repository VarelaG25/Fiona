package com.refuge.data.dummy

import com.refuge.data.local.dao.PetDao

object DatabaseSeeder {

    suspend fun seedPets(petDao: PetDao) {

        // evita duplicados (muy importante)
        if (petDao.getAllPets().isNotEmpty()) return

        petDao.insertAll(PetsDummy.pets)
    }
}