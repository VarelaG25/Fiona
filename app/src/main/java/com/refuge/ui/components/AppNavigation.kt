package com.refuge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.refuge.ui.screens.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.refuge.data.session.SessionManager
import com.refuge.presentation.session.PetSessionViewModel
import com.refuge.presentation.viewmodel.PetDetailViewModel
import com.refuge.presentation.viewmodel.ProfileViewModel
import com.refuge.presentation.viewmodel.SessionViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val sessionViewModel: SessionViewModel = hiltViewModel()
    val isLogged by sessionViewModel.isLogged.collectAsState(initial = false)

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                isLoggedIn = isLogged,
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable("pets") {
            PetsScreen(
                isLoggedIn = isLogged,
                onBackClick = { navController.popBackStack() },
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                },
                onDogClick = { id ->
                    navController.navigate("pet_detail/$id")
                }
            )
        }

        composable("login") {
            LoginScreen(
                viewModel = hiltViewModel(),
                onBackClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = false }
                        launchSingleTop = true
                    }
                },
                onRegisterClick = { navController.navigate("register") },
                onLoginSuccess = {
                    navController.navigate("profile") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                viewModel = hiltViewModel(),
                onBackClick = { navController.popBackStack() },
                onLoginClick = { navController.navigate("login") }
            )
        }

        composable("pet_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()

            PetDetailScreen(
                petId = id,
                onBackClick = { navController.popBackStack() },
                onProfileClick = {
                    if (isLogged) navController.navigate("profile")
                    else navController.navigate("login")
                },
                onAdoptClick = {
                    navController.navigate("adoption_form/$id")
                }
            )
        }

        composable("adoption_form/{id}") { backStackEntry ->
            val rawId = backStackEntry.arguments?.getString("id")
            val id = rawId?.toIntOrNull()
            AdoptionFormScreen(
                petId = id,
                onBackClick = { navController.popBackStack() },
                onNavigateToSuccess = {
                    navController.navigate("adoption_success")
                }
            )
        }

        composable("adoption_success") {
            AdoptionSuccessScreen(
                onBackToHome = { navController.navigate("home") }
            )
        }

        composable("profile") {
            ProfileScreen(
                viewModel = hiltViewModel(),
                isLoggedIn = isLogged,
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}