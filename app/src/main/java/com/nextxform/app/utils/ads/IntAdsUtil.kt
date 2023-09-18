package com.nextxform.app.utils.ads

import android.app.Application
import android.content.Context
import android.util.Log
import com.nextxform.adman.interstitial.InterstitialAdsUtil
import com.nextxform.app.BuildConfig
import com.nextxform.app.Application as MyApp

private const val TAG = "Log"

class IntAdsUtil(context: Context) : InterstitialAdsUtil(context) {
    override fun logEvent(event: String) {
        Log.d(TAG, event)
    }

    override fun loadAllAds() {
        IntAds.values().forEach(::requestAd)
    }

    override fun isDebug(): Boolean = BuildConfig.DEBUG
}