package com.example.uilibrary.util

import com.example.uilibrary.common.analytics.AnalyticsEvent
import com.example.uilibrary.common.analytics.AnalyticsHelper
import com.example.uilibrary.coreui.Routes
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class AppNavigatorImpl
    @Inject
    constructor(private val analyticsHelper: AnalyticsHelper) : AppNavigator {
        override val navigationChannel =
            Channel<NavigationIntent>(
                capacity = Int.MAX_VALUE,
                onBufferOverflow = BufferOverflow.DROP_LATEST,
            )

        override suspend fun navigateBack(
            route: String?,
            inclusive: Boolean,
        ) {
            navigationChannel.send(
                NavigationIntent.NavigateBack(
                    route = route,
                    inclusive = inclusive,
                ),
            )
        }

        override fun tryNavigateBack(
            route: String?,
            inclusive: Boolean,
        ) {
            navigationChannel.trySend(
                NavigationIntent.NavigateBack(
                    route = route,
                    inclusive = inclusive,
                ),
            )
        }

        override suspend fun navigateTo(
            route: String,
            popUpToRoute: String?,
            inclusive: Boolean,
            isSingleTop: Boolean,
        ) {
            navigationChannel.send(
                NavigationIntent.NavigateTo(
                    route = route,
                    popUpToRoute = popUpToRoute,
                    inclusive = inclusive,
                    isSingleTop = isSingleTop,
                ),
            )
        }

        override fun tryNavigateTo(
            route: String,
            popUpToRoute: String?,
            inclusive: Boolean,
            isSingleTop: Boolean,
        ) {
            val selectedBottomTab =
                when (route.substringBefore("/")) {
                    Routes.TAB_01.route -> 0
                    Routes.TAB_02.route -> 1
                    Routes.TAB_03.route -> 2
                    Routes.TAB_04.route -> 3
                    Routes.TAB_05.route -> 4
                    else -> 0
                }
            analyticsHelper.logEvent(AnalyticsEvent.Types.SCREEN_VIEW, route.substringBefore("/"))
            navigationChannel.trySend(
                NavigationIntent.NavigateTo(
                    route = route,
                    popUpToRoute = popUpToRoute,
                    inclusive = inclusive,
                    isSingleTop = isSingleTop,
                    selectedBottomTab = selectedBottomTab,
                ),
            )
        }

        override suspend fun deepLinkTo(
            deepLink: String,
            inclusive: Boolean,
        ) {
            navigationChannel.send(
                NavigationIntent.DeepLinkTo(
                    deepLink = deepLink,
                    inclusive = inclusive,
                ),
            )
        }

        override fun tryDeepLinkTo(
            deepLink: String,
            inclusive: Boolean,
        ) {
            navigationChannel.trySend(
                NavigationIntent.DeepLinkTo(
                    deepLink = deepLink,
                    inclusive = inclusive,
                ),
            )
        }

        override fun tryCloseApp() {
            navigationChannel.trySend(
                NavigationIntent.CloseApp,
            )
        }
    }
