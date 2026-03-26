package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdoptionSuccessContent(
    onBackToHome: () -> Unit
) {
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
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text("Volver al Inicio", color = Color.Black, fontWeight = FontWeight.Bold)
    }
}