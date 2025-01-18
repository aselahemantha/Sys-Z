package com.example.uilibrary.coreui

sealed class NavigationItem(
    protected val route: String,
    vararg params: Any,
) {
    val fullRoute: String =
        if (params.isEmpty()) {
            route
        } else {
            val builder = StringBuilder(route)
            params.forEach { builder.append("/{$it}") }
            builder.toString()
        }

    sealed class NoArgumentsDestination(
        route: String,
    ) : NavigationItem(route) {
        operator fun invoke(): String = route
    }

    object SplashScreen : NoArgumentsDestination(Routes.SPLASH.route)

    object LoginScreen : NoArgumentsDestination(Routes.LOGIN.route)

    object Tab01 : NoArgumentsDestination(Routes.TAB_01.route)

    // object Tab02 : NoArgumentsDestination(Routes.TAB_02)

    object Tab02 : NavigationItem(Routes.TAB_02.route, "tabPosition") {
        operator fun invoke(tabPosition: String): String =
            route.appendParams(
                "tabPosition" to tabPosition,
            )
    }

    object Tab03 : NoArgumentsDestination(Routes.TAB_03.route)

    object Tab04 : NoArgumentsDestination(Routes.TAB_04.route)

    object Tab05 : NoArgumentsDestination(Routes.TAB_05.route)



//    object CuratedListScreen : NavigationItem(
//        Routes.CURATED_LIST.route,
//        "title",
//    ) {
//        operator fun invoke(title: String): String =
//            route.appendParams(
//                "title" to title,
//            )
//    }
//
//    object NewsExpandableScreen : NoArgumentsDestination(Routes.NEWS.route)
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)
    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }
    return builder.toString()
}
