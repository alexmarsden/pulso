package com.example.pulso.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.health.services.client.data.DataTypeAvailability

@Composable
fun MeasureDataScreen(
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
    }
}