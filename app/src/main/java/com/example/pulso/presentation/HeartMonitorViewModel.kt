package com.example.pulso.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.health.services.client.data.DataTypeAvailability
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulso.data.HealthServicesRepository
import com.example.pulso.data.MeasureMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeartMonitorViewModel @Inject constructor(
    private val healthServicesRepository: HealthServicesRepository
) : ViewModel() {
    val availability: MutableState<DataTypeAvailability> =
        mutableStateOf(DataTypeAvailability.UNKNOWN)
    val heartRate: MutableState<Double> = mutableDoubleStateOf(0.0)
    val uiState: MutableState<UiState> = mutableStateOf(UiState.Startup)

    init {
        viewModelScope.launch {
            val isSupported = healthServicesRepository.hasHeartRateCapability()
            uiState.value = if (isSupported) {
                UiState.Supported
            } else {
                UiState.NotSupported
            }
        }

        viewModelScope.launch {
            healthServicesRepository.heartRateMeasureFlow()
                .collect { measureMessage ->
                    when (measureMessage) {
                        is MeasureMessage.MeasureAvailability -> {
                            availability.value = measureMessage.availability
                        }
                        is MeasureMessage.MeasureData -> {
                            heartRate.value = measureMessage.data.last().value
                        }
                    }
                }
        }
    }
}

sealed class UiState {
    data object NotSupported : UiState()
    data object Startup : UiState()
    data object Supported : UiState()
}