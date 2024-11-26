package com.ajtech.cxinifnity

import android.app.Application
import com.oracle.cx.mobilesdk.ORABaseDataCollector

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ORABaseDataCollector.setApplication(this)
    }
}