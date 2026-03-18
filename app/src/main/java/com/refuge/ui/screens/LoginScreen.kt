package com.refuge.ui.screens

import com.refuge.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Barra superior
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Text("Login", modifier = Modifier.align(Alignment.Center), fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = null,
                modifier = Modifier.size(220.dp).clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.Crop
            )

            Text("Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text("Help us find a home for every friend", textAlign = TextAlign.Center, color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Login Logic */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Login", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
                Text("New to the shelter? ", color = Color.Gray)
                TextButton(onClick = onRegisterClick) {
                    Text("Create an account", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}