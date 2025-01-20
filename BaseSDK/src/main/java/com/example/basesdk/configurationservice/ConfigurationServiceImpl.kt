/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.basesdk.configurationservice

import com.example.basesdk.BuildConfig
import com.example.basesdk.domain.configurationservice.ConfigurationService
import com.google.firebase.Firebase
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.performance
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.gtn.basesdk.domain.configurationservice.trace
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ConfigurationServiceImpl
    @Inject
    constructor() : ConfigurationService {
        private val remoteConfig
            get() = Firebase.remoteConfig

        private val performance
            get() = Firebase.performance

        init {
            if (BuildConfig.DEBUG) {
                val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 }
                remoteConfig.setConfigSettingsAsync(configSettings)
            }

//            remoteConfig.setDefaultsAsync(AppConfig.remote_config_defaults)
        }

//        override suspend fun fetchConfiguration(): Boolean = trace(FETCH_CONFIG_TRACE) { remoteConfig.fetchAndActivate().await() }

        override fun getPulseRemoteConfig(): FirebaseRemoteConfig = remoteConfig

        override fun getFirebasePerformance(): FirebasePerformance = performance

        override val isShowLoginText: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN].asBoolean()

        companion object {
            // Firebase Components

            // Home screen
            const val SHOW_HOME_SCREEN = "show_home_screen_top_card"
        }
    }
