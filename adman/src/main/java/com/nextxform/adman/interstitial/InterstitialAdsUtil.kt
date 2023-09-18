package com.nextxform.adman.interstitial

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdRequest.*
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.nextxform.adman.Constants
import com.pawanroy1997.versionchecker.Checker
import kotlin.math.log

abstract class InterstitialAdsUtil(private val context: Context) {
    private var adRequest: AdRequest = Builder().build()

    abstract fun logEvent(event: String): Unit
    abstract fun loadAllAds(): Unit
    abstract fun isDebug(): Boolean

    private lateinit var activity: Activity

    private fun assignAdInstance(type: InterstitialAdTypeInterface, loadedAd: InterstitialAd?) {
        if (loadedAd == null) {
            logEvent("LoadedAd is null")
            return
        }

        loadedAd.fullScreenContentCallback = getFullscreenContentCallBack()
        type.interstitialAd = loadedAd
    }

    private fun loadIntAd(type: InterstitialAdTypeInterface) {
        val adUnitId = if (isDebug()) Constants.TestIds.INTERSTITIAL else type.getAdUnitId()
        InterstitialAd.load(
            context,
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
                    logEvent("AdLoaded")
                    if (Checker.isBelowJELLY_BEAN_MR1) return
                    if (activity.isFinishing || activity.isDestroyed) return
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

    fun showAd(type: InterstitialAdTypeInterface, activity: Activity) {
        this.activity = activity
        type.interstitialAd?.show(activity) ?: requestAd(type)
    }

    fun requestAd(type: InterstitialAdTypeInterface) {
        loadIntAd(type)
    }
}