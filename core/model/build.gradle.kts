plugins {
    id("newsfusion.kotlin.library")
    id("kotlinx-serialization")
}


dependencies {
    implementation(libs.kotlinx.serialization.json)
}