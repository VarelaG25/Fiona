package com.refuge.di

import android.content.Context
import androidx.room.Room
import com.refuge.data.local.AppDatabase
import com.refuge.data.local.dao.UsuarioDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "refuge_database" // Este será el nombre físico de tu archivo de base de datos
        )
            // .fallbackToDestructiveMigration() // Opcional: Borra la BD si cambias la estructura en el futuro
            .build()
    }

    @Provides
    @Singleton
    fun provideUsuarioDao(db: AppDatabase): UsuarioDao {
        return db.usuarioDao()
    }
}