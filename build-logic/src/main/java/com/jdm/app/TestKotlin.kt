package com.jdm.app

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureKotest() {
    configureJUnit()
}

internal fun Project.configureJUnit() {
    val libs = extensions.libs
    dependencies {
        "testImplementation"(libs.findLibrary("junit").get())
    }
}
