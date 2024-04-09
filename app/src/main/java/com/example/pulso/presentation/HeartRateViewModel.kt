package com.example.pulso.presentation

import kotlinx.coroutines.flow.MutableStateFlow

class HeartRateViewModel {
    val state: MutableStateFlow<HeartRateState> = MutableStateFlow(HeartRateState.Loading)
}

sealed class HeartRateState {
    data object Loading : HeartRateState()
    data object Content : HeartRateState()
    data object Error : HeartRateState()
}