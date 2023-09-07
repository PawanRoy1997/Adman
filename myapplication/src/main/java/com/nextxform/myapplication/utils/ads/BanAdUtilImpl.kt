package com.nextxform.myapplication.utils.ads

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.nextxform.adman.banner.BannerAdsUtil
import com.nextxform.myapplication.ANALYTICS_TAG

@SuppressLint("StaticFieldLeak")
object BanAdUtilImpl: BannerAdsUtil() {
    override fun loadAllAds(activity: Activity) {}

    override fun isDebug(): Boolean = true

    override fun logEvent(event: String) {
        Log.d(ANALYTICS_TAG, event)
    }
}