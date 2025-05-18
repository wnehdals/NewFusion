plugins {
    id("newsfusion.android.library")
    id("newsfusion.android.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.jdm.app.core.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}