plugins {
    id("newsfusion.android.library")
}

android {
    namespace = "com.jdm.app.testing"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    //implementation(libs.androidx.core.ktx)
    //testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}