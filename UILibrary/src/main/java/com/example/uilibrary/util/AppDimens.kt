package com.example.uilibrary.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import com.example.uilibrary.coreui.theme.Dimens
import com.example.uilibrary.coreui.theme.MediumScreenDimensions

@Composable
fun ProvideAppDimens(
    appDimens: Dimens,
    content: @Composable () -> Unit,
) {
    val appDimensLocal = remember { appDimens }
    CompositionLocalProvider(LocalAppDimens provides appDimensLocal) {
        content()
    }
}

val LocalAppDimens =
    compositionLocalOf {
        MediumScreenDimensions
    }

val ScreenOrientation
    @Composable
    get() = LocalConfiguration.current.orientation
