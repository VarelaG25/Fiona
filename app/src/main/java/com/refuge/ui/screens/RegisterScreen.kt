package com.refuge.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.refuge.R
import com.refuge.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.delay

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class
)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onBackClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {

    val state = viewModel.state.collectAsState()

    /*
    =========================
    SUCCESS ANIMATION
    =========================
    */

    val successScale by animateFloatAsState(
        targetValue = if (state.value.isSuccess) 1f else 0.7f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        ),
        label = ""
    )

    val successAlpha by animateFloatAsState(
        targetValue = if (state.value.isSuccess) 1f else 0f,
        animationSpec = tween(400),
        label = ""
    )

    /*
    =========================
    ERROR SHAKE ANIMATION
    =========================
    */

    val shakeOffset = remember { Animatable(0f) }

    LaunchedEffect(state.value.message) {

        if (
            state.value.message != null &&
            !state.value.isSuccess
        ) {

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
    AUTO HIDE SUCCESS
    =========================
    */

    LaunchedEffect(state.value.isSuccess) {

        if (state.value.isSuccess) {

            delay(1500)

            onLoginClick()
        }
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Register",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    IconButton(onClick = onBackClick) {

                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },

                actions = {
                    Spacer(modifier = Modifier.width(48.dp))
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },

        containerColor = Color.White

    ) { padding ->

        Box(modifier = Modifier.fillMaxSize()) {

            /*
            =========================
            MAIN CONTENT
            =========================
            */

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
            ) {

                /*
                =========================
                BANNER
                =========================
                */

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(24.dp))
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.dog_group),
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
                            .padding(16.dp)
                    ) {

                        Surface(
                            color = Color(0xFFFFD54F),
                            shape = RoundedCornerShape(8.dp)
                        ) {

                            Text(
                                "FIONA REFUGIO",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            "Start Your Journey",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Join Our Family",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Help us provide a forever home for our furry friends.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                /*
                =========================
                FORM
                =========================
                */

                RegisterTextField(
                    label = "Full Name",
                    placeholder = "John Doe",
                    icon = Icons.Outlined.Person,
                    value = state.value.fullName,
                    onValueChange = viewModel::onFullNameChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                RegisterTextField(
                    label = "Email",
                    placeholder = "hello@example.com",
                    icon = Icons.Outlined.Email,
                    value = state.value.email,
                    onValueChange = viewModel::onEmailChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                RegisterTextField(
                    label = "Phone",
                    placeholder = "+52 000 000 0000",
                    icon = Icons.Outlined.Phone,
                    value = state.value.phone,
                    onValueChange = viewModel::onPhoneChange
                )

                Spacer(modifier = Modifier.height(16.dp))

                RegisterTextField(
                    label = "Password",
                    placeholder = "Min 8 characters",
                    icon = Icons.Outlined.Lock,
                    value = state.value.password,
                    onValueChange = viewModel::onPasswordChange,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                /*
                =========================
                REGISTER BUTTON
                =========================
                */

                Button(
                    onClick = {
                        viewModel.registerUser()
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD54F)
                    ),

                    shape = RoundedCornerShape(14.dp)
                ) {

                    Text(
                        "Create Account",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }

                /*
                =========================
                GENERIC ERROR
                =========================
                */

                AnimatedVisibility(
                    visible =
                        state.value.message != null &&
                                !state.value.isSuccess,

                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut()
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
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
                                text = "Unable to complete registration.",
                                color = Color.Red,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Text("Already have an account? ")

                    Text(
                        "Log in",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            onLoginClick()
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            /*
            =========================
            SUCCESS OVERLAY
            =========================
            */

            AnimatedVisibility(
                visible = state.value.isSuccess,
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
                                "Registration Successful",
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                "Welcome to the shelter family!",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterTextField(
    label: String,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {

    Column {

        Text(
            label,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
            value = value,

            onValueChange = onValueChange,

            placeholder = {
                Text(placeholder)
            },

            leadingIcon = {
                Icon(icon, contentDescription = null)
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(12.dp),

            visualTransformation =
                if (isPassword)
                    PasswordVisualTransformation()
                else
                    androidx.compose.ui.text.input.VisualTransformation.None
        )
    }
}