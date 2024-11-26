package com.ajtech.cxinifnity

import android.app.Application
import com.oracle.cx.mobilesdk.ORABaseDataCollector
import com.oracle.cx.mobilesdk.utils.ORALogger

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ORABaseDataCollector.setApplication(this)
        ORALogger.setLogLevel(ORALogger.INFO)
    }
}