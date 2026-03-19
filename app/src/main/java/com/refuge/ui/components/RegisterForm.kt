package com.refuge.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun RegisterForm() {
    RegisterTextField("Full Name", "John Doe", Icons.Outlined.Person)
    Spacer(modifier = Modifier.height(16.dp))

    RegisterTextField("Email Address", "hello@example.com", Icons.Outlined.Email)
    Spacer(modifier = Modifier.height(16.dp))

    RegisterTextField("Phone Number", "+1 (555) 000-0000", Icons.Outlined.Phone)
    Spacer(modifier = Modifier.height(16.dp))

    RegisterTextField("Password", "Min. 8 characters", Icons.Outlined.Lock, true)

    Spacer(modifier = Modifier.height(32.dp))
}