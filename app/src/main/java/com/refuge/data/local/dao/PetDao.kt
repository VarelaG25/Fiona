package com.refuge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.refuge.data.local.entity.PetEntity
import com.refuge.data.local.entity.AdopterEntity

@Dao
interface PetDao {

    // =========================
    // PETS
    // =========================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: PetEntity)

    @Query("SELECT * FROM pets")
    suspend fun getAllPets(): List<PetEntity>

    @Query("SELECT * FROM pets WHERE nombre LIKE '%' || :name || '%'")
    suspend fun getPetsByName(name: String): List<PetEntity>

    @Query("SELECT * FROM pets WHERE id = :id LIMIT 1")
    suspend fun getPetById(id: Int): PetEntity?

    // =========================
    // ADOPTION (solo inserts por IDs)
    // =========================

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdoption(adoption: AdopterEntity)

    @Insert
    suspend fun insertAll(pets: List<PetEntity>)

    @Query("""
    SELECT p.* 
    FROM pets p
    INNER JOIN adoptions a ON p.id = a.idPet
    WHERE a.idUsuario = :userId
""")
    suspend fun getAdoptedPets(userId: Int): List<PetEntity>
}