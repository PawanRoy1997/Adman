package com.nextxform.adman.interstitial

import android.app.Activity
import android.app.Application
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdRequest.*
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlin.math.log

abstract class InterstitialAdsUtil {
    private var adRequest: AdRequest = Builder().build()

    abstract fun logEvent(event: String): Unit
    abstract fun loadAllAds(): Unit
    abstract fun getApplication(): Application

    private fun assignAdInstance(type: InterstitialAdTypeInterface, loadedAd: InterstitialAd?) {
        if (loadedAd == null) {
            logEvent("LoadedAd is null")
            return
        }

        loadedAd.fullScreenContentCallback = getFullscreenContentCallBack()
        type.interstitialAd = loadedAd
    }

    fun loadIntAd(type: InterstitialAdTypeInterface) {
        var adUnitId = type.getAdUnitId()
        InterstitialAd.load(
            getApplication(),
            adUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    handleAdError(p0)
                    logEvent("AdLoadFailed")
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    super.onAdLoaded(p0)
                    assignAdInstance(type, p0)
                }
            })
    }

    private fun getFullscreenContentCallBack(): FullScreenContentCallback {
        return object : FullScreenContentCallback() {
            override fun onAdClicked() {
                super.onAdClicked()
                logEvent("AdClicked")
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                logEvent("AdDismissedFullScreenContent")
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                logEvent("AdFailedToShowFullScreenContent")
            }

            override fun onAdImpression() {
                super.onAdImpression()
                logEvent("AdImpression")
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
                logEvent("AdShowedFullScreenContent")
            }
        }
    }

    private fun handleAdError(loadAdError: LoadAdError) {
        when (loadAdError.code) {
            ERROR_CODE_REQUEST_ID_MISMATCH -> logEvent("Error: Request Mismatch")
            ERROR_CODE_INTERNAL_ERROR -> logEvent("Error: Internal Error")
            ERROR_CODE_NETWORK_ERROR -> logEvent("Error: Network Error")
            ERROR_CODE_NO_FILL -> logEvent("Error: No Fill")
            ERROR_CODE_APP_ID_MISSING -> logEvent("Error: ID is missing")
            ERROR_CODE_INVALID_AD_STRING -> logEvent("Error: Invalid ad String")
            ERROR_CODE_INVALID_REQUEST -> logEvent("Error: Invalid Request")
            ERROR_CODE_MEDIATION_NO_FILL -> logEvent("Error: Mediation no fill")
        }
    }

    fun showAd(type: InterstitialAdTypeInterface, activity: Activity){
        type.interstitialAd?.show(activity)?: requestAd(type)
    }

    fun requestAd(type:InterstitialAdTypeInterface){
        loadIntAd(type)
    }
}