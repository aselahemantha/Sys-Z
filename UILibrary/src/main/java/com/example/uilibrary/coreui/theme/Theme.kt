package com.example.uilibrary.coreui.theme

import android.app.Activity
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.uilibrary.util.LocalAppDimens
import com.example.uilibrary.util.ProvideAppDimens
import com.example.uilibrary.util.enums.AppTheme
import com.gtn.uilibrary.coreui.theme.MediumTypography
import com.gtn.uilibrary.coreui.theme.SmallTypography

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AuthenticationAppTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    dynamicColor: Boolean = true,
    activity: Activity = LocalContext.current as ComponentActivity,
    content: @Composable () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val isLightMode =
        remember(configuration) {
            configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_NO
        }
    val colorScheme =
        if (appTheme == AppTheme.SYSTEM) {
            if (isLightMode) {
                LightColorPalette
            } else {
                DarkColorPalette
            }
        } else if (appTheme == AppTheme.LIGHT) {
            LightColorPalette
        } else {
            DarkColorPalette
        }

    val window = calculateWindowSizeClass(activity = activity)
    val localConfig = LocalConfiguration.current

    var typography = MediumTypography
    var appDimens = MediumScreenDimensions

    when (window.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (localConfig.screenWidthDp <= 360) {
                appDimens = SmallScreenDimensions
                typography = SmallTypography
            } else if (localConfig.screenWidthDp < 599) {
                appDimens = MediumScreenDimensions
                typography = MediumTypography
            }
        }
    }

    val customColorsPalette =
        if (appTheme == AppTheme.SYSTEM) {
            if (isLightMode) {
                LightCustomColorsPalette
            } else {
                DarkCustomColorsPalette
            }
        } else if (appTheme == AppTheme.LIGHT) {
            LightCustomColorsPalette
        } else {
            DarkCustomColorsPalette
        }

    ProvideAppDimens(appDimens = appDimens) {
        CompositionLocalProvider(
            LocalCustomColorsPalette provides customColorsPalette,
        ) {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = typography,
                shapes = Shapes,
                content = content,
            )
        }
    }
}

enum class ScreenSize {
    SMALL,
    MEDIUM,
    LARGE,
}

@Composable
fun getScreenSize(): ScreenSize {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    return when {
        screenWidth < 360.dp -> ScreenSize.SMALL
        screenWidth in 360.dp..600.dp -> ScreenSize.MEDIUM
        else -> ScreenSize.LARGE
    }
}

val MaterialTheme.dimens
    @Composable get() = LocalAppDimens.current
