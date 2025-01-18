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

package com.gtn.basesdk.domain.configurationservice

import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

interface ConfigurationService {
    suspend fun fetchConfiguration(): Boolean

    fun getPulseRemoteConfig(): FirebaseRemoteConfig?

    val isShowHomeScreenNews: Boolean
    val isShowHomeScreenMostTraded: Boolean
    val isShowHomeScreenShortcutButtons: Boolean
    val isShowHomeScreenPortfolioPosition: Boolean
    val isShowHomeScreenGlobalMarkets: Boolean
    val isShowHomeScreenCuratedList: Boolean
    val isShowHomeScreenTopCard: Boolean
    val isShowPortfolioScreenTopCard: Boolean
    val baseURL: String
    val isForceChartEnable: Boolean
    val isForcePositionsEnable: Boolean
    val isWatchlistTapUnable: Boolean

    fun getFirebasePerformance(): FirebasePerformance?
}
