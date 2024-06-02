package com.ihfazh.dailytrackerchild

import android.app.Application

class DailyTrackerChildApplication: Application() {
    lateinit var compositionRoot : ApplicationCompositionRoot
    override fun onCreate() {
        super.onCreate()
        compositionRoot = ApplicationCompositionRoot(this)
    }

}