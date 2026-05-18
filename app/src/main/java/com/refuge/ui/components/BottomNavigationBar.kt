package com.refuge.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val PrimaryYellow = Color(0xFFFFD700)

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    isLoggedIn: Boolean,
    onItemClick: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { onItemClick("home") },
            icon = { Icon(Icons.Outlined.Home, null) },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == "pets",
            onClick = { onItemClick("pets") },
            icon = { Icon(Icons.Default.Pets, null) },
            label = { Text("Pets") }
        )

        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = {
                if (isLoggedIn) onItemClick("profile")
                else onItemClick("login")
            },
            icon = { Icon(Icons.Outlined.Person, null) },
            label = { Text("Profile") }
        )
    }
}