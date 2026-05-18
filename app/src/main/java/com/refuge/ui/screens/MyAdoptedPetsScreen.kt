package com.refuge.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.refuge.presentation.viewmodel.AdoptedPetsViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.refuge.data.session.SessionManager
import com.refuge.presentation.viewmodel.PetDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAdoptedPetsScreen(
    onBackClick: () -> Unit,
    onPetClick: (Int) -> Unit,
    onNavigate: (String) -> Unit
) {

    val viewModel: AdoptedPetsViewModel = hiltViewModel()
    val pets by viewModel.state.collectAsState()

    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userId by sessionManager.getUserId().collectAsState(initial = null)

    LaunchedEffect(userId) {
        userId?.let {
            viewModel.loadAdoptedPets(it)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Mis Adoptados", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            if (pets.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Aún no has adoptado ningún perrito 🐶")
                }
                return@Column
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pets) { pet ->
                    PetCard(
                        pet = pet,
                        onClick = { onPetClick(pet.id) }
                    )
                }
            }
        }
    }
}