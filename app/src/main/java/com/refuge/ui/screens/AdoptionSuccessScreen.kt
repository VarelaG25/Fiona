package com.refuge.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refuge.R

@Composable
fun AdoptionSuccessScreen(
    onBackToHome: () -> Unit,
    onViewApplications: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFFFFD700),
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Solicitud Enviada!",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "Pronto nos pondremos en contacto contigo para continuar el proceso.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onBackToHome,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Volver al Inicio", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}