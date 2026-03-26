package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size

@Composable
fun AdoptionSuccessIcon() {
    Icon(
        Icons.Default.CheckCircle,
        contentDescription = null,
        tint = Color(0xFFFFD700),
        modifier = Modifier.size(100.dp)
    )
}