package com.example.uilibrary.coreui

import com.example.uilibrary.R

enum class Routes(val route: String, val routeId: Int, val imageRes: Int = 1, val nameRes: Int = 1) {
    WELCOME("welcome", 0),
    SPLASH("splash", 1),
    LOGIN("login", 2),
    TAB_01("tab01-device", 3, R.drawable.device_bottom_bar, R.string.device),
    TAB_02("tab02-battery", 4, R.drawable.battery_bottom_bar, R.string.battery),
    TAB_03("tab03-temp", 5, R.drawable.temp_bottom_bar, R.string.temp),
    TAB_04("tab04-sensors", 6, R.drawable.sensor_bottom_bar, R.string.sensors),
    TAB_05("tab05-about", 7, R.drawable.about_us_bottom_bar, R.string.about_us),
    ;

    companion object {
        fun fromRoute(route: String): Routes {
            return values().find { it.route == route } ?: TAB_01
        }

        fun fromRouteId(routeId: Int): Routes {
            return values().find { it.routeId == routeId } ?: TAB_01
        }
    }
}
