package com.example.uilibrary.view.dashboard.main

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.NavHostController
import com.example.basesdk.domain.usecase.auth.ConnectivityCheckerUseCase
import com.example.uilibrary.common.analytics.AnalyticsEvent
import com.example.uilibrary.common.analytics.AnalyticsHelper
import com.example.uilibrary.coreui.Routes
import com.example.uilibrary.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
open class DashBoardViewModel
    @Inject
    constructor(
        private val context: Context,
        private val appNavigator: AppNavigator,
        private val connectivityCheckerUseCase: ConnectivityCheckerUseCase,
        private val analyticsHelper: AnalyticsHelper,
        // tab01
    ) : BaseViewModel(
            context = context,
            connectivityCheckerUseCase = connectivityCheckerUseCase,
        ) {
        private val isOnboardComplete = MutableStateFlow(false)
        private val isOnboardCompleteState: LiveData<Boolean> = isOnboardComplete.asLiveData()
        val navigationChannel = appNavigator.navigationChannel
        val selectedBottomNavTab = mutableStateOf(Routes.TAB_01)


        fun onCompleteOnboard(isComplete: Boolean = false) {
            isOnboardComplete.value = isComplete
        }


        fun onBottomNavigate(
            navCon: NavHostController,
            selectedTab: Int = 0,
            selectedRoute: Routes = Routes.TAB_01,
        ) {
            this.selectedBottomNavTab.value = selectedRoute
            navCon.navigate(if (selectedRoute == Routes.TAB_02) selectedRoute.route + "/$selectedTab" else selectedRoute.route)
            analyticsHelper.logEvent(AnalyticsEvent.Types.SCREEN_VIEW, selectedRoute.route)
        }
    }
