package com.ajtech.cxinifnity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ajtech.cxinifnity.event.Event
import com.ajtech.cxinifnity.state.MainState
import com.ajtech.cxinifnity.ui.theme.CXInifnityTheme
import com.ajtech.cxinifnity.usecase.GetDeviceIdUseCase

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        inject()
        startUI()
    }

    private fun inject() {
        viewModel = MainViewModel(GetDeviceIdUseCase(this))
        lifecycle.addObserver(viewModel)
    }

    private fun startUI() {
        setContent {
            val state = viewModel.state.collectAsState()
            CXInifnityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayDeviceId(innerPadding, state.value) {
                        viewModel.onEvent(Event.OnViewDisplayed(it))
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayDeviceId(paddingValues: PaddingValues, state: MainState, trackView: (String) -> Unit) {
    when(state) {
        is MainState.Loading -> CircularProgressIndicator(
            modifier = Modifier.size(45.dp).padding(paddingValues)
        )
        is MainState.HasDeviceId -> DeviceIdScreen(modifier = Modifier.padding(paddingValues), state) {
            trackView(it)
        }
        is MainState.Error -> Text("Error", modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun DeviceIdScreen(modifier: Modifier, state: MainState.HasDeviceId, trackView: (String) -> Unit = {}) {
    Column(modifier = modifier) {
        Text(
            "Device ID: ${state.deviceId}",
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        )

        Text(
            "Source: ${state.source}",
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        )
    }

    SideEffect { trackView(state.deviceId) }
}