package com.example.uilibrary.view.dashboard.tab01

import android.os.Build
import com.example.uilibrary.util.AppNavigator
import com.example.uilibrary.view.dashboard.main.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Tab01ViewModel
    @Inject
    constructor(
        private val appNavigator: AppNavigator,
    ) : BaseViewModel() {

    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    val device = Build.DEVICE
    val brand = Build.BRAND
    val hardware = Build.HARDWARE
    val serial = Build.SERIAL // Deprecated; use `Build.getSerial()`
    val versionRelease = Build.VERSION.RELEASE
    val versionSdk = Build.VERSION.SDK_INT

        fun onBackButtonClicked() {
            appNavigator.tryNavigateBack()
        }

    }
