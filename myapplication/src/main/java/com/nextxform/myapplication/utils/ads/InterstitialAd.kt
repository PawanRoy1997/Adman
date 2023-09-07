package com.nextxform.myapplication.utils.ads

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.nextxform.adman.Constants
import com.nextxform.adman.interstitial.InterstitialAdTypeInterface

object InterstitialAd : InterstitialAdTypeInterface{
    override var interstitialAd: InterstitialAd? = null

    override fun getAdUnitId(): String = Constants.TestIds.INTERSTITIAL
}