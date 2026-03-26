package com.refuge.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.refuge.ui.components.AdoptionSuccessIcon
import com.refuge.ui.components.AdoptionSuccessContent

@Composable
fun AdoptionSuccessScreen(
    onBackToHome: () -> Unit,
    onViewApplications: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AdoptionSuccessIcon()

        Spacer(modifier = Modifier.height(24.dp))

        AdoptionSuccessContent(onBackToHome)
    }
}