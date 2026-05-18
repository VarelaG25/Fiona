package com.refuge

import android.app.Application
import com.refuge.data.dummy.DatabaseSeeder
import com.refuge.data.local.AppDatabase
import com.refuge.di.DatabaseModule
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class RefugeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            val db = DatabaseModule.provideDatabase(this@RefugeApplication)
            val petDao = db.petDao()

            if (petDao.getAllPets().isEmpty()) {
                DatabaseSeeder.seedPets(petDao)
            }
        }
    }
}