package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import com.refuge.ui.theme.PrimaryYellow

@Composable
fun HomeStats() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("120+", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = PrimaryYellow)
            Text("Dogs Rescued", color = Color.Gray)
        }

        Column {
            Text("85", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = PrimaryYellow)
            Text("Monthly Donors", color = Color.Gray)
        }
    }
}