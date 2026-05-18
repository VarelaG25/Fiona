package com.refuge.ui.screens

import com.refuge.R
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refuge.presentation.viewmodel.LoginViewModel
import androidx.compose.foundation.layout.statusBarsPadding

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onBackClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {

    val state by viewModel.state.collectAsState()

    /*
    =========================
    SUCCESS ANIMATION
    =========================
    */

    val successScale by animateFloatAsState(
        targetValue = if (state.isLoginSuccess) 1f else 0.7f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        ),
        label = ""
    )

    val successAlpha by animateFloatAsState(
        targetValue = if (state.isLoginSuccess) 1f else 0f,
        animationSpec = tween(400),
        label = ""
    )

    /*
    =========================
    ERROR SHAKE ANIMATION
    =========================
    */

    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage != null) {

            repeat(4) {
                shakeOffset.animateTo(
                    targetValue = 12f,
                    animationSpec = tween(50)
                )

                shakeOffset.animateTo(
                    targetValue = -12f,
                    animationSpec = tween(50)
                )
            }

            shakeOffset.animateTo(
                targetValue = 0f,
                animationSpec = tween(50)
            )
        }
    }

    /*
    =========================
    LOGIN SUCCESS
    =========================
    */

    LaunchedEffect(state.isLoginSuccess) {
        if (state.isLoginSuccess) {

            kotlinx.coroutines.delay(1200)

            onLoginSuccess()

            viewModel.resetSuccessState()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            /*
            =========================
            MAIN CONTENT
            =========================
            */

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // TOP BAR

                Box(modifier = Modifier.fillMaxWidth()) {

                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                    Text(
                        "Login",
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // IMAGE

                Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = null,
                    modifier = Modifier
                        .size(220.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Welcome Back",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    "Help us find a home for every friend",
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // EMAIL

                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEmailChange(it)
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // PASSWORD

                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                /*
                =========================
                GENERIC ERROR MESSAGE
                =========================
                */

                AnimatedVisibility(
                    visible = state.errorMessage != null,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut()
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .offset(x = shakeOffset.value.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFEBEE)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.Error,
                                contentDescription = null,
                                tint = Color.Red
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Invalid credentials. Please try again.",
                                color = Color.Red,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // LOGIN BUTTON

                Button(
                    onClick = {
                        viewModel.loginUser()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD700)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !state.isLoading
                ) {

                    if (state.isLoading) {

                        CircularProgressIndicator(
                            color = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )

                    } else {

                        Text(
                            "Login",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 24.dp)
                ) {

                    Text(
                        "New to the shelter? ",
                        color = Color.Gray
                    )

                    TextButton(onClick = onRegisterClick) {

                        Text(
                            "Create an account",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }

            /*
            =========================
            SUCCESS OVERLAY
            =========================
            */

            AnimatedVisibility(
                visible = state.isLoginSuccess,
                enter = fadeIn(),
                exit = fadeOut()
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.95f),
                    contentAlignment = Alignment.Center
                ) {

                    Card(
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(
                                horizontal = 40.dp,
                                vertical = 32.dp
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier
                                    .size(90.dp)
                                    .scale(successScale)
                                    .alpha(successAlpha)
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Text(
                                "Login Successful",
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                "Welcome back!",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}