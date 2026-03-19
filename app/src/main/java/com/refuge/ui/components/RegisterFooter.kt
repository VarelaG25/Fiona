package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RegisterFooter(onLoginClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("Already have an account? ", color = Color.Gray)
        Text(
            "Log in",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onLoginClick() }
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(3) {
            Icon(
                Icons.Default.Pets,
                contentDescription = null,
                tint = PrimaryYellow.copy(alpha = 0.6f),
                modifier = Modifier
                    .size(16.dp)
                    .padding(horizontal = 4.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}