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
    api(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.coroutines.test)
    api(libs.junit)
    api(projects.core.data)
    api(projects.core.model)

}