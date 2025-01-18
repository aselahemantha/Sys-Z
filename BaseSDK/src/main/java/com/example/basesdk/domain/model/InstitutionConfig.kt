package com.example.basesdk.domain.model


data class NavConfig(
    val BottomNav: List<NavItem>,
)

data class NavItem(
    val route: String,
    val langKey: String,
    val icon: String,
    val screenId: String,
)

data class HomeScreenConfig(
    val shortcutButtons: List<ShortcutButtonsItem>,
    val globalMarkets: MutableList<GlobalMarketItem>,
    val curatedList: MutableList<CuratedListItem>,
)

data class ShortcutButtonsItem(
    val route: String = "",
    val icon: String = "",
    val title: String = "",
)

data class GlobalMarketItem(
    // val icon: String,
    val title: String,
    val key: String,
    // val subTitle: String,
)

data class CuratedListItem(
    val icon: String,
    val title: String,
    val subTitle: String,
)

data class SpotItem(
    val exchange: String,
    val symbol: String,
)

data class SpotOTConfig(
    val spots: MutableList<SpotItem>,
)

data class ConfigedDefinedWL(
    val exchange: String,
    val wlname: String,
)

/*data class MostTradedListItem (
    val countryCode: String,
    val logo: String,
    val symbol: String,
    val description: String,
    val price: String,
    val change: String,
    val percentChange: String
)*/

data class ScreenConfig(
    val loginScreen: List<WidgetConfig>,
    val welcomeScreen: List<WidgetConfig>,
    val tab01Screen: List<WidgetConfig>,
    val tab02Screen: List<WidgetConfig>,
    val tab03Screen: List<WidgetConfig>,
    val tab04Screen: List<WidgetConfig>,
)

data class WidgetConfig(
    val WidgetId: String,
)

data class LoginConfig(
    val assistancePhone: String,
    val assistanceEmail: String,
    val isNewUser: Boolean,
)

data class ConfigType(
    val configValue: String,
)

data class WelcomeConfig(
    val welcomeText: String,
)

data class ColorConfig(
    val primaryColor: String,
)

data class AppTokens(
    val accessToken: String,
)

data class InstitutionConfig(
    val NavConfig: NavConfig,
    val ScreenConfig: ScreenConfig,
    val loginConfig: LoginConfig,
    val configType: ConfigType,
    val welcomeConfig: WelcomeConfig,
    val colorConfig: ColorConfig,
    val appToken: AppTokens,
    val homeScreenConfig: HomeScreenConfig,
    val spotOTConfig: SpotOTConfig,
    val predefinedList: List<ConfigedDefinedWL>,
)

// data class LocalizationConfig(
//    val defaultLang: String,
//    val supportedLangMeta: Map<String, LangMeta>
// )
// data class LangMeta(
//    val LANG_DES: String,
//    val LANG_DIR: String,
//    val LANG_CODE: String
// )
