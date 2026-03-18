package com.refuge.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.refuge.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionFormScreen(
    onBackClick: () -> Unit,
    onNavigateToSuccess: () -> Unit
) {
    var checked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Adoption Application", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Imagen de cabecera
            Box(modifier = Modifier.fillMaxWidth().height(180.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.padding(24.dp)) {
                Text("Complete your application", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(24.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = checked, onCheckedChange = { checked = it })
                    Text("I agree to the adoption terms and conditions", fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { onNavigateToSuccess() },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                    shape = RoundedCornerShape(16.dp),
                    enabled = checked
                ) {
                    Text("Submit Application", color = Color.Black, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}