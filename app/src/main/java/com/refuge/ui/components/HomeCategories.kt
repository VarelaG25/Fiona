package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.vector.ImageVector

import com.refuge.ui.theme.PrimaryYellow

@Composable
fun HomeCategories() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        HomeCategoryItem("Medical Care", Icons.Default.MedicalServices, Modifier.weight(1f))
        HomeCategoryItem("Nutrition", Icons.Default.Restaurant, Modifier.weight(1f))
        HomeCategoryItem("Shelter", Icons.Default.Home, Modifier.weight(1f))
    }
}

@Composable
fun HomeCategoryItem(label: String, icon: ImageVector, modifier: Modifier) {
    Surface(
        color = Color(0xFFFEF9E7),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.height(100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, tint = PrimaryYellow)
            Text(label, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}