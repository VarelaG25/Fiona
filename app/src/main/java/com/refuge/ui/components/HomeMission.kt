package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier

import com.refuge.ui.theme.PrimaryYellow

@Composable
fun HomeMission() {
    Column {
        Row {
            HorizontalDivider(
                modifier = Modifier.width(40.dp),
                thickness = 4.dp,
                color = PrimaryYellow
            )
            Text("  OUR MISSION", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        }

        Text("A Haven for the Forgotten", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Text(
            "Fiona Refugio Animal is a non-profit organization dedicated to the rescue and rehabilitation of street dogs.",
            color = Color.Gray
        )
    }
}