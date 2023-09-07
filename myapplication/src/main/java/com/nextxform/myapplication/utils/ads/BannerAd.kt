package com.nextxform.myapplication.utils.ads

import com.google.android.gms.ads.AdView
import com.nextxform.adman.Constants
import com.nextxform.adman.banner.BannerAdTypeInterface

object BannerAd : BannerAdTypeInterface{
    override fun tag(): String = "Dummy Banner"

    override fun id(): String = Constants.TestIds.BANNER_TEST_ID

    override var isLoaded: Boolean = false
    override var view: AdView? = null
}