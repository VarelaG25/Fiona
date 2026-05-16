package com.refuge.domain.usecase

import com.refuge.data.repository.UsuarioRepository
import com.refuge.domain.mapper.toEntity
import com.refuge.domain.model.Usuario
import javax.inject.Inject // !!! IMPORTANTE IMPORTAR !!!

class RegisterUserUseCase @Inject constructor( // !!! AGREGA ESTO AQUÍ !!!
    private val repository: UsuarioRepository
) {
    suspend operator fun invoke(usuario: Usuario) {
        repository.insertUser(usuario.toEntity())
    }
}