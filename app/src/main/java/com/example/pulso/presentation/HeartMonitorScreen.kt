package com.example.pulso.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.health.services.client.data.DataTypeAvailability
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.Icon

@Composable
fun HeartMonitorScreen(
    availability: DataTypeAvailability,
    heartRate: Double
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeartRateLabel(
            availability,
            heartRate
        )
        Button(
            onClick = { /* Do something */ },
        ) {
            Icon(
                imageVector = Icons.Default.HourglassTop,
                contentDescription = "Start timer",
                modifier = Modifier.size(ButtonDefaults.DefaultIconSize).wrapContentSize(),
            )
        }
    }
}