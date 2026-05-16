package com.refuge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.refuge.data.local.entity.UsuarioEntity

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM usuario WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UsuarioEntity?
}