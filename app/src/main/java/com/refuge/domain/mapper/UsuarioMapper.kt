package com.refuge.domain.mapper

import com.refuge.data.local.entity.UsuarioEntity
import com.refuge.domain.model.Usuario

fun UsuarioEntity.toDomain(): Usuario {
    return Usuario(
        id = id,
        fullName = fullName,
        email = email,
        phone = phone,
        password = password
    )
}

fun Usuario.toEntity(): UsuarioEntity {
    return UsuarioEntity(
        id = id,
        fullName = fullName,
        email = email,
        phone = phone,
        password = password
    )
}