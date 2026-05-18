package com.refuge.domain.mapper

import com.refuge.data.local.entity.AdopterEntity
import com.refuge.domain.model.Adopter

fun AdopterEntity.toDomain() = Adopter(
    id = id,
    idUsuario = idUsuario,
    idPet = idPet
)

fun Adopter.toEntity() = AdopterEntity(
    id = id,
    idUsuario = idUsuario,
    idPet = idPet
)