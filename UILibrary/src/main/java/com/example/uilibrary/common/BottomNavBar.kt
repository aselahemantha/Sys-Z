package com.example.uilibrary.common

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.basesdk.domain.model.InstitutionConfig
import com.example.basesdk.domain.model.NavItem
import com.example.uilibrary.R
import com.example.uilibrary.coreui.Routes
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.coreui.theme.NoRippleTheme
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth
import com.example.uilibrary.view.dashboard.main.BaseNotification
import com.example.uilibrary.view.dashboard.main.NotifierType
import com.gtn.basesdk.util.enums.ConnectionState
import com.example.basesdk.util.enums.AppState

@Composable
fun BottomNavBar(
    selectedTabIndex: Routes = Routes.TAB_01,
    onSelect: (Routes) -> Unit,
    institutionConfig: InstitutionConfig?,
    baseNotification: BaseNotification = BaseNotification(),
) {
    var backPressed by remember { mutableStateOf(false) }

    val dispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    DisposableEffect(dispatcherOwner) {
        val callback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPressed = true
                    // viewModel.navigateToBack()
                }
            }
        dispatcherOwner?.onBackPressedDispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Box(
            modifier =
                Modifier
                    .height(55f.scaleHeight()),
        ) {
            NavigationBar(
                modifier =
                    Modifier
                        .height(55f.scaleHeight())
                        .shadow(elevation = 15.dp),
                containerColor = LocalCustomColorsPalette.current.figmaColors.Background0,
                contentColor = LocalCustomColorsPalette.current.figmaColors.Background0,
                tonalElevation = 10.dp,
            ) {
                val bottomNavItems =
                    listOf(
                        NavItem(
                            route = "tab01-device",
                            langKey = "Device",
                            icon = "Device",
                            screenId = "Device",
                        ),
                        NavItem(
                            route = "tab02-battery",
                            langKey = "Battery",
                            icon = "Battery",
                            screenId = "Battery",
                        ),
                        NavItem(
                            route = "tab03-temp",
                            langKey = "Temperature",
                            icon = "Temperature",
                            screenId = "Temperature",
                        ),
                        NavItem(
                            route = "tab04-sensors",
                            langKey = "Sensors",
                            icon = "Sensors",
                            screenId = "Sensors",
                        ),
                        NavItem(
                            route = "tab05-about",
                            langKey = "About",
                            icon = "About",
                            screenId = "About",
                        ),
                    )

                bottomNavItems.forEachIndexed { index, item ->
                    var routeVal = Routes.fromRoute(item.route)
                    val iconColor =
                        if (selectedTabIndex == routeVal) {
                            LocalCustomColorsPalette.current.figmaColors.Primary0
                        } else {
                            LocalCustomColorsPalette.current.figmaColors.Primary30
                        }
                    BottomNavigationItem(
                        modifier = Modifier.height(80f.scaleHeight()),
                        icon = {
                            Image(
                                painter = painterResource(id = routeVal.imageRes),
                                contentDescription = "bottom navigation icons",
                                modifier =
                                    Modifier
                                        .size(24f.scaleWidth())
                                        .testTag(routeVal.route),
                                colorFilter = ColorFilter.tint(color = iconColor),
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = routeVal.nameRes),
                                color = iconColor,
                                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                                fontWeight = FontWeight.Medium,
                                fontSize = 11f.scaleFontSize(),
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                            )
                        },
                        selected = (selectedTabIndex == routeVal),
                        onClick = { onSelect(routeVal) },
                    )
                }
            }
            Row(
                modifier =
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
            ) {
                Box(
                    modifier =
                        Modifier
                            .height(1.dp)
                            .weight(1f),
                ) {
                    if (baseNotification.notifier.equals(NotifierType.CHANGE) ||
                        baseNotification.notifier.equals(
                            NotifierType.LOADING,
                        )
                    ) {
                        LinearProgressIndicator(
                            modifier =
                                Modifier
                                    .height(1.dp)
                                    .fillMaxWidth(),
                            // .align(Alignment.TopCenter)
                            color =
                                if (baseNotification.notifier.equals(NotifierType.CHANGE)) {
                                    LocalCustomColorsPalette.current.figmaColors.Warning0
                                } else if (baseNotification.notifier.equals(NotifierType.LOADING)) {
                                    LocalCustomColorsPalette.current.figmaColors.Primary0
                                } else {
                                    Color.Transparent
                                },
                            trackColor =
                                if (baseNotification.notifier.equals(NotifierType.CHANGE)) {
                                    LocalCustomColorsPalette.current.figmaColors.Warning100
                                } else if (baseNotification.notifier.equals(NotifierType.LOADING)) {
                                    LocalCustomColorsPalette.current.figmaColors.Primary100
                                } else {
                                    Color.Transparent
                                },
                        )
                    }
                }
                Box(
                    modifier =
                        Modifier
                            .size(2.dp, 1.dp),
                )
                Box(
                    modifier =
                        Modifier
                            .size(2.dp, 1.dp),
                )
            }
        }
    }
}
