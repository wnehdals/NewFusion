plugins {
    id("newsfusion.android.library")
}

android {
    namespace = "com.jdm.app.core.domain"
}

dependencies {
    implementation(project(mapOf("path" to ":core:model")))
    implementation(project(mapOf("path" to ":core:data")))
}