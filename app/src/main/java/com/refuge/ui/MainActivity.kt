package com.refuge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.refuge.ui.navigation.AppNavigation
import com.refuge.ui.theme.RefugeTheme
// 1. IMPORTA LA ANOTACIÓN DE HILT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // 2. COLOCA ESTA LÍNEA AQUÍ (Obligatoria para inyectar ViewModels)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RefugeTheme {
                AppNavigation()
            }
        }
    }
}