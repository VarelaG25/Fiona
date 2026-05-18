package com.refuge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.refuge.R
import com.refuge.data.local.entity.PetEntity
import com.refuge.presentation.viewmodel.PetDetailViewModel
import com.refuge.ui.components.BottomNavigationBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    onBackClick: () -> Unit,
    onNavigate: (String) -> Unit,
    onAdoptClick: () -> Unit,
    petId: Int?
) {
    val viewModel: PetDetailViewModel = hiltViewModel()
    val pet by viewModel.pet.collectAsState()

    // Cargar pet al entrar
    LaunchedEffect(petId) {
        petId?.let { viewModel.loadPet(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = pet?.nombre ?: "Loading...",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Share, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "pets",
                isLoggedIn = false,
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
    ) { paddingValues ->

        if (pet == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        val currentPet = pet

        if (currentPet == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {

            // IMAGE
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = currentPet?.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(32.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            // NAME + LOCATION (puedes cambiar luego si tienes campo ciudad)
            Text(
                text = currentPet.nombre,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = currentPet.raza,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // INFO BOXES
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DetailBox("AGE", "${currentPet.edad} years", Modifier.weight(1f))
                DetailBox("BREED", currentPet.raza, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // DESCRIPTION
            Text(
                text = "His Story",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = currentPet.descripcion,
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ADOPT BUTTON
            Button(
                onClick = onAdoptClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFD700)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Adopt ${currentPet.nombre}",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun DetailBox(
    label: String,
    value: String,
    modifier: Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0xFFF1F5F9),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                fontWeight = FontWeight.Bold
            )
        }
    }
}