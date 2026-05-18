package com.refuge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.refuge.data.local.dao.UsuarioDao
import com.refuge.data.local.dao.PetDao
import com.refuge.data.local.entity.UsuarioEntity
import com.refuge.data.local.entity.PetEntity
import com.refuge.data.local.entity.AdopterEntity

@Database(
    entities = [
        UsuarioEntity::class,
        PetEntity::class,
        AdopterEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun petDao(): PetDao
}