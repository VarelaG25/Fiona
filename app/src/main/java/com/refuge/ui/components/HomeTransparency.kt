package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.vector.ImageVector

import com.refuge.ui.theme.PrimaryYellow

@Composable
fun HomeTransparency() {
    Surface(
        color = Color(0xFFF1F5F9),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Transparency", fontWeight = FontWeight.Bold)
                Text("Full History →", color = PrimaryYellow, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            HomeTransparencyRow(
                "Medical Supplies Q3",
                "September 2023",
                "$2,450.00",
                Color(0xFFD1FAE5),
                Icons.Default.Description
            )

            Spacer(modifier = Modifier.height(8.dp))

            HomeTransparencyRow(
                "Food Distributions",
                "August 2023",
                "$1,890.00",
                Color(0xFFDBEAFE),
                Icons.Default.Assignment
            )
        }
    }
}