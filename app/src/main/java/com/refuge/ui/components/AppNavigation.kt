package com.refuge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.refuge.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        // Pantalla de Inicio
        composable("home") {
            HomeScreen(
                onNavigateToLogin = { navController.navigate("login") },
                onNavigateToPets = { navController.navigate("pets") }
            )
        }

        // Listado de Perros
        composable("pets") {
            PetsScreen(
                onBackClick = { navController.popBackStack() },
                onProfileClick = { navController.navigate("login") },
                onDogClick = { navController.navigate("pet_detail") } // Al clicar uno, va al detalle
            )
        }

        // Autenticación
        composable("login") {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onRegisterClick = { navController.navigate("register") }
            )
        }

        composable("register") {
            RegisterScreen(
                onBackClick = { navController.popBackStack() },
                onLoginClick = { navController.navigate("login") }
            )
        }

        composable("pet_detail") {
            PetDetailScreen(
                onBackClick = { navController.popBackStack() },
                onProfileClick = { navController.navigate("login") },
                onAdoptClick = { navController.navigate("adoption_form") } // <--- ESTO CONECTA LAS PANTALLAS
            )
        }

        composable("adoption_form") {
            AdoptionFormScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToSuccess = {
                    navController.navigate("adoption_success") {
                        // Esto borra el formulario del historial para que no pueda volver atrás
                        popUpTo("adoption_form") { inclusive = true }
                    }
                }
            )
        }

        composable("adoption_success") {
            AdoptionSuccessScreen(
                onBackToHome = { navController.navigate("home") },
                onViewApplications = { /* ... */ }
            )
        }
    }
}