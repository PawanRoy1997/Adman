package com.nextxform.adman.interstitial

import com.google.android.gms.ads.interstitial.InterstitialAd

interface InterstitialAdTypeInterface {
    var interstitialAd: InterstitialAd?
    fun getAdUnitId():String
}