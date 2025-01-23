package com.example.uilibrary.util

import android.os.Build
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat

@Composable fun AppStatusBarColor(
    statusBarColor: Color,
    navigationBarColor: Color,
) {
    val activity = androidx.compose.ui.platform.LocalContext.current as? ComponentActivity ?: return
    val window = activity.window

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = window.insetsController
        if (controller != null) {
            window.statusBarColor = statusBarColor.toArgb()
            window.navigationBarColor = navigationBarColor.toArgb()

            val statusBarLightContent = statusBarColor.isLight()
            val navBarLightContent = navigationBarColor.isLight()

            controller.setSystemBarsAppearance(
                (if (statusBarLightContent) WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS else 0) or
                    (if (navBarLightContent) WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS else 0),
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
            )
        }
    } else {
        // For API levels below 30, manage status bar appearance through WindowCompat
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()
        // Note: On pre-API 30, you cannot control the appearance of status bar content
    }
}

// Extension function to check if a color is light or dark
fun Color.isLight(): Boolean {
    val luma = 0.2126 * red + 0.7152 * green + 0.0722 * blue
    return luma > 0.5
}
