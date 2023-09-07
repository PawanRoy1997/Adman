package com.nextxform.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nextxform.myapplication.databinding.ActivityMainBinding
import com.nextxform.myapplication.utils.ads.BanAdUtilImpl
import com.nextxform.myapplication.utils.ads.BannerAd
import com.nextxform.myapplication.utils.ads.IntAdUtilImpl
import com.nextxform.myapplication.utils.ads.InterstitialAd

const val ANALYTICS_TAG = "Analytics"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.bottomBanner.setOnClickListener {
            BanAdUtilImpl.requestAdOfType(BannerAd).with(this).inside(binding.container).show()
        }

        binding.interstitialAds.setOnClickListener {
            IntAdUtilImpl.showAd(InterstitialAd, this)
        }
    }
}