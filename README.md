# AdMan

# Version Checker
![](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)
[![](https://jitpack.io/v/PawanRoy1997/Adman.svg)](https://jitpack.io/#PawanRoy1997/Adman)

A kotlin library for android platform to efficiently check the build version of android device which reduces the effort to continuously writing if cases.

## Installation

In settings.gradle
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

In project build.gradle

```groovy
dependencies {
    implementation 'com.github.PawanRoy1997:Adman:1.0.0'
}
```

## Usage For Banner Ads

Instead of
```kotlin
val adView = AdView(this)
adView.setAdSize(AdSize.BANNER)
adView.setAdUnitId("YOUR_AD_UNIT_ID")
val adRequest = AdRequest.Builder().build()
adView.load(adRequest)
```

Use
```kotlin
BanAdUtil().requestAdOfType(
//    YourAdType
).inside(
//    Your Linear layour for Ads
).with(this).show()
```
## Usage for Interstitial Ads

Instead of
```kotlin
val adRequest = AdRequest.Builder().build()
InterstitialAd.load(this, "YOUR_AD_UNIT_ID", adRequest, callback)
```

Use
```kotlin
val ad = object : InterstitialAdTypeInterface{
    //Your defined ad type for interstitial ad
}
InterstitialAdUtil().showAd(ad, this)
```