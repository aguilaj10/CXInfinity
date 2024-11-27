package com.ajtech.cxinifnity.tracking

import android.util.Log
import com.ajtech.cxinifnity.MainViewModel
import com.ajtech.cxinifnity.event.Event
import com.oracle.cx.mobilesdk.ORABaseDataCollector
import com.oracle.cx.mobilesdk.ORAEventMap

class MainTrackingManager {
    fun onEvent(event: Event) {
        when(event) {
            is Event.OnViewDisplayed -> onViewDisplayed(event)
            is Event.OnButtonClicked -> onButtonClicked(event)
        }
    }

    private fun onButtonClicked(event: Event.OnButtonClicked) {
        val customData = mapOf("device_id" to event.deviceId, "screen" to "MainView", "button" to event.name)

        val oraEventMap = ORAEventMap("dcsuri", "oraTi", "oraPi", "oraSys", "oraDl", customData)

        Log.d(MainViewModel::class.java.simpleName, "Triggering event: ${oraEventMap.eventMap}")
        ORABaseDataCollector.getInstance().triggerEvent(oraEventMap)
    }

    private fun onViewDisplayed(event: Event.OnViewDisplayed) {
        val customData = mapOf("device_id" to event.deviceId, "screen" to "MainView", "user_id" to "1234")

        val oraEventMap = ORAEventMap("dcsuri", "oraTi", "oraPi", "oraSys", "oraDl", customData)

        Log.d(MainViewModel::class.java.simpleName, "Triggering event: ${oraEventMap.eventMap}")
        ORABaseDataCollector.getInstance().triggerEvent(oraEventMap)
    }
}