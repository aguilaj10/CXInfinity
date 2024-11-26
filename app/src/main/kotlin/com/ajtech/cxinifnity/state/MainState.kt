package com.ajtech.cxinifnity.state

sealed interface MainState {
    data object Loading : MainState

    data class HasDeviceId(
        val deviceId: String,
        val source: String,
    ) : MainState

    data object Error: MainState
}