package com.indemand.fotd.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.indemand.fotd.android.LocalNavController
import com.indemand.fotd.android.R
import com.indemand.fotd.android.common.TransparentBottomSheetDemo
import com.indemand.fotd.android.utils.openPlayStore
import com.indemand.fotd.domain.model.BottomSheetDetails
import com.indemand.fotd.splash.SplashUiState
import com.indemand.fotd.splash.SplashViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = getViewModel()
) {
    val navController = LocalNavController.current
    val splashState = splashViewModel.splashUIFlow.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var appUpdateDialogDetails by remember { mutableStateOf<BottomSheetDetails?>(null) }

    LaunchedEffect(splashState.value) {
        when (splashState.value) {
            is SplashUiState.ToHome -> {
                navController.navigate("home") {
                    popUpTo("splash") { inclusive = true }
                }
            }

            is SplashUiState.ToLogin -> {/*navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }*/
            }

            is SplashUiState.AppUpdateDialog -> {
                showDialog = true
                appUpdateDialogDetails =
                    (splashState.value as SplashUiState.AppUpdateDialog).bottomSheetDetails
            }

            is SplashUiState.Idle -> {
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Center Image
        Image(
            painter = painterResource(id = R.drawable.ic_logo_splash),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }

    if (showDialog && appUpdateDialogDetails != null) {
        val context = LocalContext.current
        TransparentBottomSheetDemo(appUpdateDialogDetails!!, onPositiveClick = {
            openPlayStore(
                context,
                appPackageName = appUpdateDialogDetails?.positiveButton?.appPackageName.orEmpty(),
                appLink = appUpdateDialogDetails?.positiveButton?.appLink.orEmpty()
            )
            showDialog = false
        }, onNegativeClick = {
            showDialog = false
        })
    }
}