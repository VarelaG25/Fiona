package com.refuge.ui.components

import com.refuge.R
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import com.refuge.ui.theme.PrimaryYellow

@Composable
fun HomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .clip(RoundedCornerShape(28.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.sad_dog),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black.copy(alpha = 0.3f)
        ) {}

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Surface(color = PrimaryYellow, shape = RoundedCornerShape(8.dp)) {
                Text("URGENT NEED", modifier = Modifier.padding(8.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }

            Text("Every Paw Deserves a Chance", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryYellow),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.Black)
                Text(" Donate Now", color = Color.Black)
            }
        }
    }
}