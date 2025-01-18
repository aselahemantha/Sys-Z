package com.example.uilibrary.util

import kotlinx.coroutines.channels.Channel

interface AppNavigator {
    val navigationChannel: Channel<NavigationIntent>

    suspend fun navigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    fun tryNavigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    suspend fun navigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )

    fun tryNavigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )

    suspend fun deepLinkTo(
        deepLink: String,
        inclusive: Boolean = false,
    )

    fun tryDeepLinkTo(
        deepLink: String,
        inclusive: Boolean = false,
    )

    fun tryCloseApp() {
    }
}

sealed class NavigationIntent {
    data class NavigateBack(
        val route: String? = null,
        val inclusive: Boolean = false,
    ) : NavigationIntent()

    data class NavigateTo(
        val route: String,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
        val selectedBottomTab: Int? = null,
    ) : NavigationIntent()

    data class DeepLinkTo(
        val deepLink: String,
        val inclusive: Boolean = false,
    ) : NavigationIntent()

    object CloseApp : NavigationIntent()
}
