package com.example.uilibrary.util.scale

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun getCurrentScreenHeight(): Float {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp.toFloat()
    } else {
        configuration.screenHeightDp.toFloat()
    }
}
