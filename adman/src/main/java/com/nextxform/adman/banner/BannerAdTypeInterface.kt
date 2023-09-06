package com.nextxform.adman.banner

import com.google.android.gms.ads.AdView

interface BannerAdTypeInterface {
    fun tag():String
    fun id(): String
    var isLoaded: Boolean
    var view:AdView?
}