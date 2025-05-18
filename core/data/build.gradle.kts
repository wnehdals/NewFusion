plugins {
    id("newsfusion.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.jdm.app.core.data"
}

dependencies {
    implementation(project(mapOf("path" to ":core:model")))
    implementation(libs.kotlinx.serialization.json)
}