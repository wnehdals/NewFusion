plugins {
    id("newsfusion.android.application")
    alias(libs.plugins.android.application)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.jdm.app.newsfusion"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jdm.app.newsfusion"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":core:navigation")))
    implementation(project(mapOf("path" to ":core:designsystem")))
    implementation(project(mapOf("path" to ":feature:main")))
    implementation(libs.androidx.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))
}