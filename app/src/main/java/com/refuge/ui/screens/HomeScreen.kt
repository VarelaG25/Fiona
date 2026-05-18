package com.refuge.ui.screens

import com.refuge.R
import com.refuge.ui.components.BottomNavigationBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
val PrimaryYellow = Color(0xFFFFD700)

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    isLoggedIn: Boolean
){
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = "home",
                isLoggedIn = isLoggedIn,
                onItemClick = { route ->
                    when (route) {
                        "pets" -> onNavigate("pets")
                        "profile" -> onNavigate("profile")
                        "login" -> onNavigate("login")
                    }
                }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(20.dp))
            MainBannerSection()
            Spacer(modifier = Modifier.height(32.dp))
            MissionSection()
            Spacer(modifier = Modifier.height(24.dp))
            CategoriesSection()
            Spacer(modifier = Modifier.height(32.dp))
            TransparencySection()
            Spacer(modifier = Modifier.height(32.dp))
            StatsSection()
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Pets, contentDescription = null, tint = PrimaryYellow, modifier = Modifier.size(28.dp))
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text("Fiona", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Shelter", color = PrimaryYellow, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
        Icon(Icons.Default.NotificationsNone, contentDescription = null, modifier = Modifier.size(28.dp))
    }
}

@Composable
fun MainBannerSection() {
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
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = 0.3f)) {}
        Column(modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)) {
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

@Composable
fun MissionSection() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            HorizontalDivider(modifier = Modifier.width(40.dp), thickness = 4.dp, color = PrimaryYellow)
            Text("  OUR MISSION", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        }
        Text("A Haven for the Forgotten", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Fiona Refugio Animal is a non-profit organization dedicated to the rescue and rehabilitation of street dogs.", color = Color.Gray)
    }
}

@Composable
fun CategoriesSection() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        CategoryItem("Medical Care", Icons.Default.MedicalServices, Modifier.weight(1f))
        CategoryItem("Nutrition", Icons.Default.Restaurant, Modifier.weight(1f))
        CategoryItem("Shelter", Icons.Default.Home, Modifier.weight(1f))
    }
}

@Composable
fun CategoryItem(label: String, icon: ImageVector, modifier: Modifier) {
    Surface(color = Color(0xFFFEF9E7), shape = RoundedCornerShape(16.dp), modifier = modifier.height(100.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(icon, contentDescription = null, tint = PrimaryYellow)
            Text(label, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TransparencySection() {
    Surface(color = Color(0xFFF1F5F9), shape = RoundedCornerShape(24.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Transparency", fontWeight = FontWeight.Bold)
                Text("Full History →", color = PrimaryYellow, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            TransparencyRow("Medical Supplies Q3", "September 2023", "$2,450.00", Color(0xFFD1FAE5), Icons.Default.Description)
            Spacer(modifier = Modifier.height(8.dp))
            TransparencyRow("Food Distributions", "August 2023", "$1,890.00", Color(0xFFDBEAFE), Icons.Default.Assignment)
        }
    }
}

@Composable
fun TransparencyRow(title: String, date: String, amount: String, iconBg: Color, icon: ImageVector) {
    Surface(color = Color.White, shape = RoundedCornerShape(12.dp)) {
        Row(modifier = Modifier.padding(12.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Surface(color = iconBg, shape = RoundedCornerShape(8.dp), modifier = Modifier.size(40.dp)) {
                Icon(icon, null, modifier = Modifier.padding(8.dp), tint = Color.Gray)
            }
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(date, fontSize = 11.sp, color = Color.Gray)
            }
            Text(amount, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StatsSection() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
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