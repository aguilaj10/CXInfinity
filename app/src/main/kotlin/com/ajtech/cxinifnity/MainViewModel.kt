package com.ajtech.cxinifnity

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajtech.cxinifnity.state.MainState
import com.ajtech.cxinifnity.usecase.GetDeviceIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val deviceIdUseCase: GetDeviceIdUseCase
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
}