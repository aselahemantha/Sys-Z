import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}
android {
    namespace = "com.example.starter"
    compileSdk = ProjectConfig.compileSdk
    android.buildFeatures.buildConfig = true

    defaultConfig {
        applicationId = "com.innovateX.sysz"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.compilerVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
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

dependencies {
    implementation(project(":UILibrary"))
    implementation(project(":BaseSDK"))
    val composeVersion = "1.5.4"
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    // implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    implementation("androidx.compose.material:material-icons-extended:$composeVersion")

    // dagger
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // dagger hilt test
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

    // Timber for logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // DataDog
    implementation("com.datadoghq:dd-sdk-android-compose:2.5.1")
    implementation("com.datadoghq:dd-sdk-android-rum:2.5.1")

    // Mockito
    testImplementation("org.mockito:mockito-core:4.8.0")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-messaging")

    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    androidTestImplementation("com.google.firebase:firebase-messaging")
    androidTestImplementation("com.google.firebase:firebase-config")
    androidTestImplementation("com.google.firebase:firebase-perf")
    androidTestImplementation("com.google.firebase:firebase-analytics")

    // Chucker

    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    // Window Size Class
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0")

    // Splash
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Credentials support
    implementation("androidx.credentials:credentials:1.2.2")
    // optional - needed for credentials support from play services, for devices running Android 13 and below.
    implementation("androidx.credentials:credentials-play-services-auth:1.2.2")

    // Play services for Google authentication (for credentials)
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // Biometric library
    implementation("androidx.biometric:biometric:1.1.0")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // AutoValue
    implementation("com.google.auto.value:auto-value-annotations:1.10.1")
    kapt("com.google.auto.value:auto-value:1.10.1")

    implementation("androidx.palette:palette-ktx:1.0.0")

    // Credentials support
    implementation("androidx.credentials:credentials:1.2.2")
    implementation("com.google.android.gms:play-services-auth:20.6.0")
}