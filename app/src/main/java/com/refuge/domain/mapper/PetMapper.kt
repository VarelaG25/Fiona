package com.refuge.domain.mapper

import com.refuge.data.local.entity.PetEntity
import com.refuge.domain.model.Pet

fun PetEntity.toDomain() = Pet(
    id = id,
    nombre = nombre,
    edad = edad,
    raza = raza,
    descripcion = descripcion
)

fun Pet.toEntity() = PetEntity(
    id = id,
    nombre = nombre,
    edad = edad,
    raza = raza,
    descripcion = descripcion
)