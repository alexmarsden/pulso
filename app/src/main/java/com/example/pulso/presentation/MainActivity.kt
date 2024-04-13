package com.example.pulso.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.example.pulso.presentation.theme.PulsoTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val heartMonitorViewModel: HeartMonitorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            PulsoApp(heartMonitorViewModel)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PulsoApp(
    heartMonitorViewModel: HeartMonitorViewModel
) {
    PulsoTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            timeText = { TimeText() }
        ) {
            val availability by heartMonitorViewModel.availability
            val heartRate by heartMonitorViewModel.heartRate
            val uiState by heartMonitorViewModel.uiState

            if (uiState == UiState.Supported) {
                val permissionState = rememberPermissionState(
                    permission = Manifest.permission.BODY_SENSORS,
                    onPermissionResult = { granted -> /* do something */ }
                )

                LaunchedEffect(permissionState) {
                    if (permissionState.status.isGranted) {
                        // do something
                    } else {
                        permissionState.launchPermissionRequest()
                    }
                }
                HeartMonitorScreen(
                    availability,
                    heartRate
                )
            }
        }
    }
}