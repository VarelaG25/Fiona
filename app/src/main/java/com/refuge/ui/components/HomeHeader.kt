package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

import com.refuge.ui.theme.PrimaryYellow

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Pets,
                contentDescription = null,
                tint = PrimaryYellow,
                modifier = Modifier.size(28.dp)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text("Fiona", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Shelter", color = PrimaryYellow, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        Icon(
            Icons.Default.NotificationsNone,
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
    }
}