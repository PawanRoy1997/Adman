package com.nextxform.app.utils.ads

import com.nextxform.adman.interstitial.InterstitialAdTypeInterface
import com.google.android.gms.ads.interstitial.InterstitialAd

enum class IntAds : InterstitialAdTypeInterface {
    LOAD_AND_LAUNCH {
        override var interstitialAd: InterstitialAd? = null

        override fun getAdUnitId(): String = ""
    },

    LOAD {
        override var interstitialAd: InterstitialAd? = null

        override fun getAdUnitId(): String = ""
    }
}