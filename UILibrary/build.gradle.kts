plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    jacoco
}

android {
    namespace = "com.example.uilibrary"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        version = "USDK-X.24.170.4"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Kotlin.jvmVersion
    }
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
        kotlinOptions {
            jvmTarget = Kotlin.jvmVersion
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
    kapt {
        correctErrorTypes = true
    }
}

detekt {
    toolVersion = "1.23.0" // Ensure this matches the plugin version
    config.setFrom("$rootDir/config/detekt/detekt.yml") // Optional: custom configuration
    buildUponDefaultConfig = true // Use default rules as the base
    allRules = false // Disable all rules; enable what you need
    autoCorrect = true // Auto-correct issues where possible
}

// ktlint config
ktlint {
    android.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

tasks.withType(Test::class) {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation(project(":BaseSDK"))
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test.ext:junit-ktx:1.2.1")
    implementation("androidx.palette:palette-ktx:1.0.0")
    testImplementation(project(":BaseSDK"))
    debugImplementation(project(":BaseSDK"))
    implementation("androidx.compose.material3:material3-window-size-class-android:1.2.0")
    testImplementation("junit:junit:4.12")
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":BaseSDK"))

    val composeVersion = "1.5.4"
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    // implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    testImplementation("io.mockk:mockk:1.12.0")
    // Espresso Intents
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.5.1")

    // Robolectric
    testImplementation("org.robolectric:robolectric:4.9")

    // dagger
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // dagger hilt test
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    // Timber for logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
    // implementation("com.squareup.retrofit2:retrofit:2.8.2")
    // implementation("com.squareup.retrofit2:converter-gson:2.8.2")
    // implementation("com.squareup.okhttp3:okhttp:4.11.0")
    // implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.material:material:1.6.2")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    androidTestImplementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-messaging")
    androidTestImplementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-config")
    androidTestImplementation("com.google.firebase:firebase-config")
    // notification permission
    implementation("com.google.accompanist:accompanist-permissions:0.31.1-alpha")
    androidTestImplementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-perf")
    androidTestImplementation("com.google.firebase:firebase-perf")
    androidTestImplementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-analytics")

    // androidTestImplementation("com.android.support.test:rules:1.0.2")

    // DataDog
    implementation("com.datadoghq:dd-sdk-android-rum:2.5.1")
    // Datadog Navigation
    implementation("com.datadoghq:dd-sdk-android-compose:2.5.1")
    // Datadog Log
    implementation("com.datadoghq:dd-sdk-android-logs:2.5.1")

    // Datadog Session Replay
    implementation("com.datadoghq:dd-sdk-android-session-replay:2.5.1")
    // Datadog material support
    implementation("com.datadoghq:dd-sdk-android-session-replay-material:2.5.1")
    // Datadog trace
    implementation("com.datadoghq:dd-sdk-android-trace:2.5.1")
    // DataDog
    implementation("com.datadoghq:dd-sdk-android-ndk:2.5.1")

    // DataDog
    implementation("com.datadoghq:dd-sdk-android-rum:2.5.1")
    // Datadog Navigation
    implementation("com.datadoghq:dd-sdk-android-compose:2.5.1")
    // Datadog Log
    implementation("com.datadoghq:dd-sdk-android-logs:2.5.1")

    // Datadog Session Replay
    implementation("com.datadoghq:dd-sdk-android-session-replay:2.5.1")
    // Datadog material support
    implementation("com.datadoghq:dd-sdk-android-session-replay-material:2.5.1")
    // Datadog trace
    implementation("com.datadoghq:dd-sdk-android-trace:2.5.1")
    // DataDog
    implementation("com.datadoghq:dd-sdk-android-ndk:2.5.1")

    // Mockito
    testImplementation("org.mockito:mockito-core:4.8.0")

    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.animation:animation-core:1.1.0")
    implementation("androidx.compose.animation:animation-graphics:1.1.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation("androidx.compose.runtime:runtime:1.6.1")

    // Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    // Window Size Class
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0")

    // Splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Credentials support
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("com.google.android.gms:play-services-auth:20.6.0")
    // optional - needed for credentials support from play services, for devices running Android 13 and below.
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")

    // Play services for Google authentication (for credentials)
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // Biometric library
    implementation("androidx.biometric:biometric:1.1.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Mockito and Mockk
    androidTestImplementation("org.mockito:mockito-core:4.8.1")
    androidTestImplementation("org.mockito:mockito-android:4.8.1")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
    androidTestImplementation("com.jakewharton.espresso:okhttp3-idling-resource:1.0.0")
    // androidTestImplementation("io.mockk:mockk-android:1.13.2")
    // androidTestImplementation("com.squareup.okhttp3:mockwebserver:5.0.0-alpha.9")

    // Tradingview charts
    implementation("com.tradingview:lightweightcharts:4.0.0")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // image
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.browser:browser:1.3.0")

    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}