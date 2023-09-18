package com.nextxform.app

import android.annotation.SuppressLint
import android.app.Application
import com.nextxform.app.utils.ads.IntAdsUtil
import android.app.Application as AndroidApp

class Application: AndroidApp() {
    companion object{
        private lateinit var application: Application
        @SuppressLint("StaticFieldLeak")
        private lateinit var intAdsUtil: IntAdsUtil
        fun getApp() = application
        fun getIntAdUtil() = intAdsUtil
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        intAdsUtil = IntAdsUtil(this)
    }
}