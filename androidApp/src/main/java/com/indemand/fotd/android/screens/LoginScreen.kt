package com.indemand.fotd.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.indemand.fotd.android.LocalNavController
import com.indemand.fotd.android.R
import com.indemand.fotd.android.common.ButtonGreySolid
import com.indemand.fotd.android.common.CustomSnackbar
import com.indemand.fotd.android.common.PrimaryInputTextField
import com.indemand.fotd.android.common.SecondaryInputTextField
import com.indemand.fotd.domain.uistate.LoginUiState
import com.indemand.fotd.viewmodel.login.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Suppress("FunctionName")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = getViewModel()) {
    val navController = LocalNavController.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginUiState = loginViewModel.loginUIState.collectAsState()
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(loginUiState.value) {
        when (loginUiState.value) {
            is LoginUiState.ToHome -> {
                navController.navigate("main") {
                    popUpTo("login") { inclusive = true }
                }
            }

            is LoginUiState.ToForgotPassword -> {
                navController.navigate("forgotPass")
            }

            is LoginUiState.ToRegister -> {
            }

            is LoginUiState.ShowError -> {
                snackbarMessage = (loginUiState.value as LoginUiState.ShowError).message
            }

            is LoginUiState.Idle -> {
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg_onboarding),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 48.dp),
        ) {
            SignUpView()
            Spacer(modifier = Modifier.height(24.dp))
            ToolbarView()
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "sign in to continue",
                style =
                    TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    ),
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "enter your email id and password to continue",
                style =
                    TextStyle(
                        color = Color(0xFF818B94),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    ),
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryInputTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = "email address",
            )
            Spacer(modifier = Modifier.height(14.dp))
            SecondaryInputTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "password",
                isInvisibleText = true,
            )
            Spacer(modifier = Modifier.height(24.dp))
            RowForgotPassView(loginViewModel, username, password)
        }
    }
    snackbarMessage?.let { message ->
        CustomSnackbar(message = message) {
            snackbarMessage = null
        }
    }
}

@Suppress("FunctionName")
@Composable
private fun SignUpView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "sign up",
            modifier = Modifier.padding(end = 8.dp),
            style =
                TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Arrow",
            tint = Color.Unspecified,
        )
    }
}

@Suppress("FunctionName")
@Composable
private fun ToolbarView() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo_login),
            contentDescription = "Arrow",
            tint = Color.Unspecified,
            modifier = Modifier.padding(end = 8.dp),
        )
        Text(
            "fact of the day",
            style =
                TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
        )
    }
}

@Suppress("FunctionName")
@Composable
private fun RowForgotPassView(
    loginViewModel: LoginViewModel,
    username: String,
    password: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "forgot password?",
            modifier =
                Modifier
                    .padding(end = 8.dp)
                    .clickable { loginViewModel.onForgotPassClick() },
            style =
                TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
        )
        ButtonGreySolid(
            text = "sign in",
            onClick = {
                loginViewModel.loginUser(username, password)
            },
        )
    }
}

@Suppress("FunctionName")
@Preview(showBackground = true, backgroundColor = 0xFF000000) // optional bg color
@Composable
fun LoginPreview() {
    LoginScreen()
}
