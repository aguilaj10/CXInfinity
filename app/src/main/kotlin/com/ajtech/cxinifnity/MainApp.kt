package com.ajtech.cxinifnity

import android.app.Application
import com.oracle.cx.mobilesdk.ORABaseDataCollector
import com.oracle.cx.mobilesdk.persistent.ORACoreDataContainer
import com.oracle.cx.mobilesdk.utils.ORALogger

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ORABaseDataCollector.setApplication(this)

        // TODO - Setup oracle.json file with correct values and uncomment the line below
        ORACoreDataContainer(this).run {
            //this.loadFromConfigFile()
        }

        ORALogger.setLogLevel(ORALogger.INFO)
    }
}