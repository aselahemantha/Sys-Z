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

        override suspend fun fetchConfiguration(): Boolean = trace(FETCH_CONFIG_TRACE) { remoteConfig.fetchAndActivate().await() }

        override fun getPulseRemoteConfig(): FirebaseRemoteConfig = remoteConfig

        override fun getFirebasePerformance(): FirebasePerformance = performance

        override val isShowHomeScreenShortcutButtons: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_SHORTCUT_BUTTONS_KEY].asBoolean()

        override val isShowHomeScreenPortfolioPosition: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_PORTFOLIO_POSITION_KEY].asBoolean()

        override val isShowHomeScreenGlobalMarkets: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_GLOBAL_MARKETS_KEY].asBoolean()

        override val isShowHomeScreenMostTraded: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_MOST_TRADED_KEY].asBoolean()

        override val isShowHomeScreenNews: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_NEWS_KEY].asBoolean()

        override val isShowHomeScreenCuratedList: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_CURATED_LIST_KEY].asBoolean()
        override val isShowHomeScreenTopCard: Boolean
            get() = remoteConfig[SHOW_HOME_SCREEN_TOP_CARD_KEY].asBoolean()

        override val isShowPortfolioScreenTopCard: Boolean
            get() = remoteConfig[SHOW_PORTFOLIO_SCREEN_TOP_CARD_KEY].asBoolean()

        override val baseURL: String
            get() = remoteConfig[BASE_URL].asString()

        override val isForceChartEnable: Boolean
            get() = remoteConfig[FORCE_CHART_ENABLE].asBoolean()

        override val isForcePositionsEnable: Boolean
            get() = remoteConfig[FORCE_POSITIONS_ENABLE].asBoolean()

        override val isWatchlistTapUnable: Boolean
            get() = remoteConfig[WATCHLIST_ITEM_TAP_ENABLE].asBoolean()

        companion object {
            // Firebase Components

            // Home screen
            const val SHOW_HOME_SCREEN_TOP_CARD_KEY = "show_home_screen_top_card"
            const val SHOW_HOME_SCREEN_SHORTCUT_BUTTONS_KEY = "show_home_screen_shortcut_buttons"
            const val SHOW_HOME_SCREEN_PORTFOLIO_POSITION_KEY = "show_home_screen_portfolio_positions"
            const val SHOW_HOME_SCREEN_GLOBAL_MARKETS_KEY = "show_home_screen_global_markets"
            const val SHOW_HOME_SCREEN_CURATED_LIST_KEY = "show_home_screen_curated_List"
            const val SHOW_HOME_SCREEN_MOST_TRADED_KEY = "show_home_screen_most_traded"
            const val SHOW_HOME_SCREEN_NEWS_KEY = "show_home_screen_news"

            // Portfolio Screen
            const val SHOW_PORTFOLIO_SCREEN_TOP_CARD_KEY = "show_portfolio_screen_top_card"

            // BaseURL
            const val BASE_URL = "base_url"

            // Traces
            private const val FETCH_CONFIG_TRACE = "fetchConfig"
            const val FETCH_AUTH_TRACE = "fetchAuth"
            const val SAVE_AUTH_CREDENTIALS_TRACE = "saveAuthCredentials"

            const val ASSET_USECASE_TRACE = "asset_usecase_trace"
            const val ASSET_USECASE_ERROR_COUNT_TRACE = "asset_usecase_error_count_trace"

            const val CUSTOMER_INFO_USECASE_TRACE = "customer_info_usecase_trace"
            const val CUSTOMER_INFO_USECASE_ERROR_COUNT_TRACE = "customer_info_usecase_error_count_trace"

            const val CURRENCY_RATE_USECASE_TRACE = "currency_rate_usecase_trace"
            const val CURRENCY_RATE_USECASE_ERROR_COUNT_TRACE = "currency_rate_usecase_error_count_trace"

            // Watchlist
            const val FORCE_CHART_ENABLE = "show_wl_chart_view"
            const val FORCE_POSITIONS_ENABLE = "show_wl_positions_view"
            const val WATCHLIST_ITEM_TAP_ENABLE = "watchlist_tap_enabled"
        }
    }
