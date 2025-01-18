plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf") version "1.4.2" apply false
    id("com.google.gms.google-services")
    jacoco
}

android {
    namespace = "com.example.basesdk"
    compileSdk = ProjectConfig.compileSdk

    android.buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        version = "BSDK-X.24.170.4"
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

    testImplementation("junit:junit:4.12")
    val composeVersion = "1.5.4"
    implementation("androidx.core:core-ktx:1.12.0")
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
    testImplementation("io.mockk:mockk:1.12.0")

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
    // implementation("com.squareup.retrofit2:retrofit:2.8.2")
    // implementation("com.squareup.retrofit2:converter-gson:2.8.2")
    // implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.9")
    // implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // AWS
    implementation("com.amazonaws:aws-android-sdk-s3:2.29.0")
    implementation("com.amazonaws:aws-android-sdk-core:2.29.0")

    // json converter
    implementation("com.google.code.gson:gson:2.10")

    // Mockito
    testImplementation("org.mockito:mockito-core:4.8.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // Chucker
    debugImplementation("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    // Window Size Class
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0")

    // Password Encryption
    implementation("org.bouncycastle:bcprov-jdk15to18:1.73")

    // Credentials support
    implementation("androidx.credentials:credentials:1.2.2")
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

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-perf")
    implementation("com.google.firebase:firebase-config")
    implementation("com.google.firebase:firebase-crashlytics")
    androidTestImplementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    androidTestImplementation("com.google.firebase:firebase-messaging")
    androidTestImplementation("com.google.firebase:firebase-config")
    androidTestImplementation("com.google.firebase:firebase-perf")
    androidTestImplementation("com.google.firebase:firebase-analytics")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:core-ktx:1.5.0")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    // Encrypted Shared Preferences
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
}