// Top-level build file where you can add configuration options common to all sub-projects/modules.

/*
The buildscript block is used to define the dependencies and variables needed by the
Gradle build system before applying plugins.
 */
buildscript {
    extra.apply {
        set("compose_version", "1.0.3")
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}
/*
The plugins block is used to apply various Gradle plugins with specific versions.
 */
plugins {
    id("com.android.application") version Gradle.version apply false
    id("com.android.library") version Gradle.version apply false
    id("org.jetbrains.kotlin.android") version Kotlin.version apply false
    id("org.jetbrains.kotlin.jvm") version Kotlin.version apply false
    id("com.google.dagger.hilt.android") version Hilt.version apply false
    id("org.jlleitschuh.gradle.ktlint") version Ktlint.version apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
}

/*
The detekt block configures the Detekt static code analysis tool for Kotlin.
 */
detekt {
    toolVersion = "1.23.0" // Ensure this matches the plugin version
    config.setFrom("$rootDir/config/detekt/detekt.yml") // Optional: custom configuration
    buildUponDefaultConfig = true // Use default rules as the base
    allRules = false // Disable all rules; enable what you need
    autoCorrect = true // Auto-correct issues where possible
}
/*
The clean task is defined to delete the project's build directory.
 */
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}