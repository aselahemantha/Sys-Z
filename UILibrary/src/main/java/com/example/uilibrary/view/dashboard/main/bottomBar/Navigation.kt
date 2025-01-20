package com.example.uilibrary.view.dashboard.main.bottomBar

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.uilibrary.coreui.NavigationItem
import com.example.uilibrary.coreui.Routes

@Composable fun Navigation(
    navController: NavHostController,
    startDestination: NavigationItem,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route,
        builder = builder,
    )
}

fun NavGraphBuilder.composable(
    destination: NavigationItem,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content,
    )
}

fun isShowBottomNavigation(
    navBackStackEntry: NavBackStackEntry?,
    destination: Any?,
): Boolean {
    val route = navBackStackEntry?.destination?.route

    return when {
        destination != null ||
//            route == Routes.SPOT_WATCHLIST_VIEW.route ||
//            route == Routes.SPOT_PROFILE_VIEW.route + "/{exchange}" + "/{symbol}" ||
//            route == Routes.SPOT_OT_VIEW.route + "/{symbol}" + "/{exchange}" + "/{tradeSide}" ||
            route == Routes.WELCOME.route ||
            route == Routes.LOGIN.route ||
            route == Routes.SPLASH.route -> false
        else -> true
    }
}

fun isOnboardRoute(
    navBackStackEntry: NavBackStackEntry?,
    destination: Any?,
): Boolean {
    val route = navBackStackEntry?.destination?.route ?: Routes.WELCOME.route

    return when {
        // destination != null ||
        route == Routes.WELCOME.route || route == Routes.LOGIN.route || route == Routes.SPLASH.route -> true

        else -> false
    }
}

fun isTabo1Route(
    navBackStackEntry: NavBackStackEntry?,
    destination: Any?,
): Boolean {
    val route = navBackStackEntry?.destination?.route ?: ""

    return when {
        // destination != null ||
        route == Routes.TAB_01.route -> true
        else -> false
    }
}
