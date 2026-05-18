package com.refuge.di

import android.content.Context
import androidx.room.Room
import com.refuge.data.local.AppDatabase
import com.refuge.data.local.dao.PetDao
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
            "refuge_database"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideUsuarioDao(db: AppDatabase): UsuarioDao = db.usuarioDao()

    @Provides
    @Singleton
    fun providePetDao(db: AppDatabase): PetDao = db.petDao()
}