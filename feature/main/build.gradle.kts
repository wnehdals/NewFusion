plugins {
    id("newsfusion.android.feature")
}

android {
    namespace = "com.jdm.app.main"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(project(mapOf("path" to ":feature:news")))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
}