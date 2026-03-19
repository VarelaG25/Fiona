package com.refuge.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.refuge.ui.components.BottomNavigationBar
import com.refuge.ui.components.SearchBar
import com.refuge.ui.components.PetsGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetsScreen(
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit,
    onDogClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Nuestros Perritos",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "pets",
                onItemClick = {
                    if (it == "profile") onProfileClick()
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {


            SearchBar()


            PetsGrid(onDogClick)
        }
    }
}