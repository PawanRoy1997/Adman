plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.nextxform.adman"
    compileSdk = 33

    defaultConfig {
        minSdk = 17

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            multiDexEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["release"])
                groupId = "com.github.PawanRoy1997"
                artifactId = "Adman"
                version = "1.0.0"
            }
        }
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.android.gms:play-services-ads-lite:22.3.0")
    implementation("com.github.PawanRoy1997:VersionChecker:2.0.0")
    implementation("androidx.multidex:multidex:2.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}