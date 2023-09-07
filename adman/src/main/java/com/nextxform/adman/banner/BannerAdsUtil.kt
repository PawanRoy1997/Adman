package com.nextxform.adman.banner

import android.app.Activity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.nextxform.adman.Constants.TestIds.BANNER_TEST_ID

abstract class BannerAdsUtil {
    private lateinit var type: BannerAdTypeInterface
    private lateinit var adViewContainer: LinearLayout
    private lateinit var baseActivity: Activity
    private lateinit var adsRequest: AdRequest

    private val defaultAdSize: AdSize
        get() {
            val outMetrics = baseActivity.resources.displayMetrics
            var adWidthPixels = LinearLayout(baseActivity).width.toFloat()
            if (adWidthPixels == 0f) adWidthPixels = outMetrics.widthPixels.toFloat()
            val adWidth = (adWidthPixels / outMetrics.density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(baseActivity, adWidth)
        }

    abstract fun loadAllAds(activity: Activity)

    abstract fun isDebug(): Boolean

    abstract fun logEvent(event: String): Unit


    fun refreshAll() {
        loadAllAds(baseActivity)
    }

    fun load(baseActivity: Activity, banAd: BannerAdTypeInterface) {
        if (banAd.isLoaded) return
        val adUnit = if (isDebug()) BANNER_TEST_ID else banAd.id()
        val adView = AdView(baseActivity).apply {
            this.tag = banAd.tag()
            this.setAdSize(defaultAdSize)
            setListOnAdsView(this)
            setAdsAttribute(this, adUnit)
        }
        banAd.view = adView
    }

    private fun setListOnAdsView(adView: AdView) {
        adView.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                logEvent("ad_closed")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                updateAdsStatus(false)
                logEvent("ad_failed")
            }

            override fun onAdOpened() {
                super.onAdOpened()
                logEvent("ad_opened")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                updateAdsStatus(true)
                logEvent("ad_loaded")
            }

            override fun onAdClicked() {
                super.onAdClicked()
                logEvent("ad_clicked")
            }

            override fun onAdImpression() {
                super.onAdImpression()
                logEvent("ad_impression")
            }
        }
    }

    private fun updateAdsStatus(isLoaded: Boolean) {
        type.isLoaded = isLoaded
    }

    private fun setAdsAttribute(adView: AdView, adUnit: String) {
        adView.adUnitId = if (isDebug()) BANNER_TEST_ID else adUnit
        loadAds(adView)
    }

    private fun loadAds(adView: AdView) {
        if (this::adsRequest.isInitialized) adView.loadAd(adsRequest)
        else logEvent("AdRequest is not Initialized")
    }

    private fun removeAdParent(adView: AdView) {
        val viewParent = adView.parent
        if (viewParent != null && viewParent is ViewGroup) viewParent.removeView(adView)
    }

    fun requestAdOfType(tag: BannerAdTypeInterface): BannerAdsUtil {
        adsRequest = AdRequest.Builder().build()
        type = tag
        return this
    }

    fun inside(viewParent: LinearLayout): BannerAdsUtil {
        adViewContainer = viewParent
        return this
    }

    fun with(adActivity: Activity): BannerAdsUtil{
        this.baseActivity = adActivity
        return this
    }

    fun show(){
        load(baseActivity, type)
        val adView = type.view!!
        removeAdParent(adView)
        adViewContainer.addView(adView)
    }
}