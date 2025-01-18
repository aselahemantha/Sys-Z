package com.example.uilibrary.coreui

enum class Routes(val route: String, val routeId: Int, val imageRes: Int = 1, val nameRes: Int = 1) {
    WELCOME("welcome", 0),
    SPLASH("splash", 1),
    LOGIN("login", 2),
    TAB_01("tab01-home", 3),
    TAB_02("tab02-portfolio", 4),
    TAB_03("tab03-watchlist", 5),
    TAB_04("tab04-trade", 6),
    TAB_05("tab05-discover", 7),
//    TAB_01("tab01-home", 3, R.drawable.home_icon, R.string.home),
//    TAB_02("tab02-portfolio", 4, R.drawable.portfolio, R.string.portfolio),
//    TAB_03("tab03-watchlist", 5, R.drawable.watchlist_icon, R.string.enter_watchlist),
//    TAB_04("tab04-trade", 6, R.drawable.trade_icon, R.string.trade),
//    TAB_05("tab05-discover", 7, R.drawable.discover, R.string.sidemenu_discover),
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
