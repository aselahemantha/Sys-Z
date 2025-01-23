package com.example.uilibrary.view.dashboard.tab04

import com.example.uilibrary.util.AppNavigator
import com.example.uilibrary.view.dashboard.main.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Tab04ViewModel
    @Inject
    constructor(
        private val appNavigator: AppNavigator,
    ) : BaseViewModel() {
        fun onBackButtonClicked() {
            appNavigator.tryNavigateBack()
        }
    }
