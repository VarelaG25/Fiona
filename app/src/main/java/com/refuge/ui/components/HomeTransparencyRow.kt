package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun HomeTransparencyRow(
    title: String,
    date: String,
    amount: String,
    iconBg: Color,
    icon: ImageVector
) {
    Surface(color = Color.White, shape = RoundedCornerShape(12.dp)) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                color = iconBg,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Icon(icon, null, modifier = Modifier.padding(8.dp), tint = Color.Gray)
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            ) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(date, fontSize = 11.sp, color = Color.Gray)
            }

            Text(amount, fontWeight = FontWeight.Bold)
        }
    }
}