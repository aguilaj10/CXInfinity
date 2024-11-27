package com.ajtech.cxinifnity

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajtech.cxinifnity.event.Event
import com.ajtech.cxinifnity.state.MainState
import com.ajtech.cxinifnity.tracking.MainTrackingManager
import com.ajtech.cxinifnity.usecase.GetDeviceIdUseCase
import com.oracle.cx.mobilesdk.ORABaseDataCollector
import com.oracle.cx.mobilesdk.ORAEventMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val deviceIdUseCase: GetDeviceIdUseCase,
    private val trackingManager: MainTrackingManager,
) : ViewModel(), DefaultLifecycleObserver {
    val state: StateFlow<MainState>
        field = MutableStateFlow<MainState>(MainState.Loading)


    override fun onCreate(owner: LifecycleOwner) {
        viewModelScope.launch {
            state.update {
                deviceIdUseCase.getDeviceId()
            }
        }
    }

    fun onEvent(event: Event) {
        trackingManager.onEvent(event)
        when(event) {
            is Event.OnViewDisplayed -> Unit
            is Event.OnButtonClicked -> onButtonClicked(event)
        }
    }

    private fun onButtonClicked(clicked: Event.OnButtonClicked) {
        // Handle button clicked
    }
}