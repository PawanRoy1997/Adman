package com.nextxform.myapplication.utils.ads

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.nextxform.adman.interstitial.InterstitialAdsUtil
import com.nextxform.myapplication.ANALYTICS_TAG
import com.nextxform.myapplication.MyApplication

@SuppressLint("StaticFieldLeak")
object IntAdUtilImpl : InterstitialAdsUtil() {
    override fun logEvent(event: String) {
        Log.d(ANALYTICS_TAG, event)
    }

    override fun loadAllAds() {}

    override fun getApplication(): Application = MyApplication.getApplication()

    override fun isDebug(): Boolean = true
}