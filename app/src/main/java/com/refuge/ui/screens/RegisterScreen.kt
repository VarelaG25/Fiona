package com.refuge.ui.screens

import com.refuge.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Color corporativo (asegúrate de que sea el mismo del proyecto)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onBackClick: () -> Unit = {}, onLoginClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Register", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = { Spacer(modifier = Modifier.width(48.dp)) }, // Para centrar el título
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            // 1. Banner Superior con el grupo de perros
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog_group), // Nombre solicitado
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Capa de degradado para legibilidad del texto
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = 0.3f)) {}

                Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                    Surface(color = PrimaryYellow, shape = RoundedCornerShape(8.dp)) {
                        Text("FIONA REFUGIO", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Text("Start Your Journey", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Título y Bienvenida
            Text("Join Our Family", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Help us provide a forever home for our furry friends and find your perfect companion.",
                color = Color.Gray, fontSize = 14.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. Formulario
            RegisterTextField(label = "Full Name", placeholder = "John Doe", icon = Icons.Outlined.Person)
            Spacer(modifier = Modifier.height(16.dp))
            RegisterTextField(label = "Email Address", placeholder = "hello@example.com", icon = Icons.Outlined.Email)
            Spacer(modifier = Modifier.height(16.dp))
            RegisterTextField(label = "Phone Number", placeholder = "+1 (555) 000-0000", icon = Icons.Outlined.Phone)
            Spacer(modifier = Modifier.height(16.dp))
            RegisterTextField(label = "Password", placeholder = "Min. 8 characters", icon = Icons.Outlined.Lock, isPassword = true)

            Spacer(modifier = Modifier.height(32.dp))

            // 4. Botón de Registro
            Button(
                onClick = { /* Lógica de registro */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow),
                shape = RoundedCornerShape(14.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text("Create Account", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Black)
            }

            // 5. Link a Login
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account? ", color = Color.Gray)
                Text("Log in", color = Color.Black, fontWeight = FontWeight.Bold, modifier = Modifier.clickable { onLoginClick() })
            }

            // Decoración final de huellas
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(3) {
                    Icon(Icons.Default.Pets, contentDescription = null, tint = PrimaryYellow.copy(alpha = 0.6f), modifier = Modifier.size(16.dp).padding(horizontal = 4.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun RegisterTextField(label: String, placeholder: String, icon: androidx.compose.ui.graphics.vector.ImageVector, isPassword: Boolean = false) {
    var value by remember { mutableStateOf("") }
    Column {
        Text(label, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = { Text(placeholder, color = Color.LightGray) },
            leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Gray) },
            trailingIcon = { if(isPassword) Icon(Icons.Default.Visibility, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFF1F5F9),
                focusedBorderColor = PrimaryYellow
            )
        )
    }
}