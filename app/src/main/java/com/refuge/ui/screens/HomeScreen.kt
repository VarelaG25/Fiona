package com.refuge.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.refuge.ui.components.*

@Composable
fun HomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToPets: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "home",
                onItemClick = { route ->
                    when (route) {
                        "profile" -> onNavigateToLogin()
                        "pets" -> onNavigateToPets()
                    }
                }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            HomeHeader()
            Spacer(modifier = Modifier.height(20.dp))

            HomeBanner()
            Spacer(modifier = Modifier.height(32.dp))

            HomeMission()
            Spacer(modifier = Modifier.height(24.dp))

            HomeCategories()
            Spacer(modifier = Modifier.height(32.dp))

            HomeTransparency()
            Spacer(modifier = Modifier.height(32.dp))

            HomeStats()
        }
    }
}