plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "newsfusion.android.hilt"
            implementationClass = "com.jdm.app.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "newsfusion.kotlin.hilt"
            implementationClass = "com.jdm.app.HiltKotlinPlugin"
        }
    }
}
