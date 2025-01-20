package com.example.uilibrary.view.dashboard.main.bottomBar

import androidx.lifecycle.ViewModel
import com.example.uilibrary.coreui.NavigationItem
import com.example.uilibrary.util.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavbarViewModel
    @Inject
    constructor(
        private val appNavigator: AppNavigator,
    ) : ViewModel() {
        fun navigateTOTab01() {
            appNavigator.tryNavigateTo(
                NavigationItem.Tab01(),
            )
        }

        fun navigateTOTab02() {
            appNavigator.tryNavigateTo(
                NavigationItem.Tab02(tabPosition = "0"),
            )
        }

        fun navigateTOTab03() {
            appNavigator.tryNavigateTo(
                NavigationItem.Tab03(),
            )
        }

        fun navigateTOTab04() {
            appNavigator.tryNavigateTo(
                NavigationItem.Tab04(),
            )
        }

        fun navigateTOTab05() {
            appNavigator.tryNavigateTo(
                NavigationItem.Tab05(),
            )
        }

        fun navigateTODashBoard() {
            appNavigator.tryNavigateTo(
                NavigationItem.LoginScreen(),
            )
        }

        fun navigateToBack() {
            appNavigator.tryNavigateBack()
        }
    }
