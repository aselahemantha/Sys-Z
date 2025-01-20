package com.example.uilibrary.view.dashboard.main.bottomBar

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.uilibrary.coreui.NavigationItem
import com.example.uilibrary.coreui.Routes
import com.example.uilibrary.util.NavigationIntent
import com.example.uilibrary.view.dashboard.main.DashBoardViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController,
    viewmodel: DashBoardViewModel = hiltViewModel(),
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route.toString(), intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    viewmodel.selectedBottomNavTab.value = Routes.fromRoute(intent.route)
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }

                is NavigationIntent.DeepLinkTo -> {
                    val deepLink = intent.deepLink
                    val destination = deepLink.substringAfterLast("")

                    if (destination == Routes.TAB_01.route) {
                        navHostController.navigate(NavigationItem.Tab01.fullRoute)
                    } else {
                        navHostController.navigate(NavigationItem.LoginScreen.fullRoute)
                    }
                }

                is NavigationIntent.CloseApp -> {
                    activity?.finishAffinity()
                }

                else -> {}
            }
        }
    }
}
