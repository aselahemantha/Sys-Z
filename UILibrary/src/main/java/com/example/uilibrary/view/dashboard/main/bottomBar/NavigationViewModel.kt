package com.example.uilibrary.view.dashboard.main.bottomBar

import androidx.lifecycle.ViewModel
import com.example.uilibrary.coreui.NavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel
    @Inject
    constructor() : ViewModel() {
        private val _currentScreen = MutableStateFlow<NavigationItem?>(null)
        val currentScreen: StateFlow<NavigationItem?> = _currentScreen

        private val _eventFlow = MutableSharedFlow<NavigationItem?>()
        val eventFlow = _eventFlow.asSharedFlow()

        suspend fun navigateToScreen(screen: NavigationItem?) {
            _currentScreen.value = screen
            _eventFlow.emit(screen)
        }

        fun clearNavigation() {
            _currentScreen.value = null
        }
    }
