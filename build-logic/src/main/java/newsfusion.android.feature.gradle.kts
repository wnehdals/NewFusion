import com.jdm.app.configureHiltAndroid
import com.jdm.app.libs

plugins {
    id("newsfusion.android.library")
    id("newsfusion.android.compose")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))


    val libs = project.extensions.libs
    implementation(libs.findLibrary("androidx.hilt.navigation.compose").get())
    implementation(libs.findLibrary("androidx.compose.navigation").get())
    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
}
