plugins {
    id("newsfusion.android.application")
}

android {
    namespace = "com.jdm.app.nesfusion"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jdm.app.nesfusion"
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
}