package com.refuge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.refuge.data.local.dao.UsuarioDao
import com.refuge.data.local.entity.UsuarioEntity

@Database(
    entities = [UsuarioEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}