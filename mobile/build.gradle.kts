plugins {
    kotlin("multiplatform") version "2.0.0"
    id("com.android.application")
}

kotlin {
    androidTarget()

    sourceSets {
        val commonMain by getting {}
    }
}

android {
    namespace = "ar.edu.um.ticketflow.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "ar.edu.um.ticketflow"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}
