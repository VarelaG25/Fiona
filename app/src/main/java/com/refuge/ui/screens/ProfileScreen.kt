package com.refuge.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refuge.presentation.viewmodel.ProfileViewModel
import com.refuge.ui.components.BottomNavigationBar

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigate: (String) -> Unit,
    isLoggedIn: Boolean,
    onLogout: () -> Unit
) {
    val userId by viewModel.userId.collectAsState(initial = null)
    val userName by viewModel.userName.collectAsState(initial = null)
    val userEmail by viewModel.userEmail.collectAsState(initial = null)

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "profile",
                isLoggedIn = isLoggedIn,
                onItemClick = { route ->
                    when (route) {
                        "pets" -> onNavigate("pets")
                        "home" -> onNavigate("home")
                        "profile" -> {}
                        "login" -> onNavigate("login")
                        "adopted" -> onNavigate("adopted")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {

            Text(
                text = "Profile",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // CARD PRINCIPAL
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Text(
                        text = userName ?: "Unknown User",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Tap to view details",
                        fontSize = 12.sp
                    )
                }
            }

            if (expanded) {

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Text("User Information", fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.height(10.dp))

                        Text("ID: ${userId ?: "-"}")
                        Text("Name: ${userName ?: "-"}")
                        Text("Email: ${userEmail ?: "-"}")

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                viewModel.logout {
                                    expanded = false
                                    onLogout()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(Icons.Default.Logout, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Cerrar sesión")
                        }
                    }
                }
            }
        }
    }
}