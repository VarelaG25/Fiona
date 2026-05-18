package com.refuge.ui.screens

import com.refuge.R
import com.refuge.ui.components.BottomNavigationBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refuge.data.local.entity.PetEntity
import com.refuge.presentation.viewmodel.PetViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.refuge.presentation.session.PetSessionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetsScreen(
    onBackClick: () -> Unit,
    onDogClick: (Int) -> Unit,
    onNavigate: (String) -> Unit,
    isLoggedIn: Boolean
) {
    val viewModel: PetViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val pets = state.pets.ifEmpty { emptyList() }
    val session: PetSessionViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Nuestros Perritos", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "pets",
                isLoggedIn = isLoggedIn,
                onItemClick = { route ->
                    when (route) {
                        "home" -> onNavigate("home")
                        "profile" -> onNavigate("profile")
                        "login" -> onNavigate("login")
                        "adopted" -> onNavigate("adopted")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar un amigo fiel...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {


                items(pets) { pet ->
                    PetCard(
                        pet = pet,
                        onClick = {
                                onDogClick(pet.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PetCard(
    pet: PetEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {

            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = pet.nombre,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pet.nombre,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                text = "${pet.raza} • ${pet.edad} años",
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}