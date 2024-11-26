package com.ajtech.cxinifnity.usecase

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.ajtech.cxinifnity.state.MainState

class GetDeviceIdUseCase(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("device_id", Context.MODE_PRIVATE)

    @SuppressLint("HardwareIds")
    suspend fun getDeviceId(): MainState.HasDeviceId {
        sharedPreferences.getString("device_id", null)?.let {
            return MainState.HasDeviceId(it, "Shared preferences")
        }

        val androidId = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        sharedPreferences.edit().putString("device_id", androidId).apply()

        return MainState.HasDeviceId(androidId, "System")
    }
}