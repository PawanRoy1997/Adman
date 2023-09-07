package com.nextxform.myapplication

import android.app.Application
import com.google.android.gms.ads.MobileAds

class MyApplication : Application() {
    companion object{
        private lateinit var application: Application

        fun getApplication(): Application = application
    }

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        application = this
    }
}