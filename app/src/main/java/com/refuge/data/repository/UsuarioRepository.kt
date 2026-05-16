package com.refuge.data.repository

import com.refuge.data.local.dao.UsuarioDao
import com.refuge.data.local.entity.UsuarioEntity
import javax.inject.Inject // !!! IMPORTANTE IMPORTAR !!!

class UsuarioRepository @Inject constructor( // !!! AGREGA ESTO AQUÍ !!!
    private val dao: UsuarioDao
) {

    suspend fun insertUser(usuario: UsuarioEntity) {
        dao.insertUsuario(usuario)
    }

    suspend fun getUserByEmail(email: String): UsuarioEntity? {
        return dao.getUserByEmail(email)
    }
}