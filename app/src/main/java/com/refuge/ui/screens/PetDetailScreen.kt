package com.refuge.ui.screens

import com.refuge.R
import com.refuge.ui.components.BottomNavigationBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAdoptClick: () -> Unit ,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fiona Refugio Animal", fontSize = 14.sp, color = Color.Gray) },
                navigationIcon = { IconButton(onClick = onBackClick) { Icon(Icons.Default.ArrowBack, null) } },
                actions = { IconButton(onClick = {}) { Icon(Icons.Default.Share, null) } }
            )
        },
        bottomBar = {
            BottomNavigationBar(currentRoute = "pets", onItemClick = { if (it == "profile") onProfileClick() })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).verticalScroll(rememberScrollState()).padding(24.dp)) {
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(300.dp).clip(RoundedCornerShape(32.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Max", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
            Text("San Salvador, El Salvador", color = Color.Gray)

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                DetailBox("AGE", "2 years", Modifier.weight(1f))
                DetailBox("BREED", "Mixed", Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("His Story", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(
                "Max is a joyful dog looking for a family. He loves to play and is very affectionate.",
                color = Color.Gray, modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 2. CONECTA EL CLICK AQUÍ
            Button(
                onClick = { onAdoptClick() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Adopt Max", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun DetailBox(label: String, value: String, modifier: Modifier) {
    Surface(modifier = modifier, color = Color(0xFFF1F5F9), shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
            Text(value, fontWeight = FontWeight.Bold)
        }
    }
}