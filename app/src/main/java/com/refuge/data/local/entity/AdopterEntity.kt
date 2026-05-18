package com.refuge.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "adoptions")
data class AdopterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val idUsuario: Int,
    val idPet: Int
)