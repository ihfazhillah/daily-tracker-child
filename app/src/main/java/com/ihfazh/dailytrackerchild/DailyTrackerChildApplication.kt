package com.ihfazh.dailytrackerchild

import android.app.Application

class DailyTrackerChildApplication: Application() {
    lateinit var compositionRoot : ActivityCompositionRoot
    override fun onCreate() {
        super.onCreate()
        compositionRoot = ActivityCompositionRoot(this)
    }
}