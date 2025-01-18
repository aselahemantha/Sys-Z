package com.example.uilibrary.coreui.theme

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// from figma
val primaryLight = Color(0xFF0416BC)
val primaryVariantLight = Color(0xFF111A75)
val secondaryLight = Color(0xFF3160F4)
val backgroundLight = Color(0xFFF5F7F9)
val surfaceLight = Color(0xFFF5F7F9)
val onPrimaryLight = Color(0xFFF5F7F9)
val onSecondaryLight = Color(0xFF000000)
val onBackgroundLight = Color(0xFF000000)
val onSurfaceLight = Color(0xFF000000)

val primaryDark = Color(0xFF0416BC)
val primaryVariantDark = Color(0xFF111A75)
val secondaryDark = Color(0xFF3160F4)
val backgroundDark = Color(0xFF121212)
val surfaceDark = Color(0xFF121212)
val onPrimaryDark = Color(0xFFF5F7F9)
val onSecondaryDark = Color(0xFF000000)
val onBackgroundDark = Color(0xFFFFFFFF)
val onSurfaceDark = Color(0xFFFFFFFF)

// ####### Default Color Palette   #######
val DarkColorPalette =
    darkColorScheme(
        primary = primaryDark,
        secondary = secondaryDark,
        background = backgroundDark,
        surface = surfaceDark,
        onPrimary = onPrimaryDark,
        onSecondary = onSecondaryDark,
        onBackground = onBackgroundDark,
        onSurface = onSurfaceDark,
    )

val LightColorPalette =
    lightColorScheme(
        primary = primaryLight,
        secondary = secondaryLight,
        background = backgroundLight,
        surface = surfaceLight,
        onPrimary = onPrimaryLight,
        onSecondary = onSecondaryLight,
        onBackground = onBackgroundLight,
        onSurface = onSurfaceLight,
    )

// ####### Custom Color Palette   #######
@Immutable
data class CustomColorsPalette(
    val extraColor1: Color = Color.Unspecified,
    val extraColor2: Color = Color.Unspecified,
    val extraColor3: Color = Color.Unspecified,
    val figmaColors: FigmaColors = FigmaColors(),
)

val LightExtraColor1 = Color(color = 0xFF29B6F6)
val LightExtraColor2 = Color(color = 0xFF26A69A)
val LightExtraColor3 = Color(color = 0xFFEF5350)

val DarkExtraColor1 = Color(color = 0xFF0277BD)
val DarkExtraColor2 = Color(color = 0xFF00695C)
val DarkExtraColor3 = Color(color = 0xFFC62828)

val LightCustomColorsPalette =
    CustomColorsPalette(
        extraColor1 = LightExtraColor1,
        extraColor2 = LightExtraColor2,
        extraColor3 = LightExtraColor3,
        figmaColors = lightFc,
    )

val DarkCustomColorsPalette =
    CustomColorsPalette(
        extraColor1 = DarkExtraColor1,
        extraColor2 = DarkExtraColor2,
        extraColor3 = DarkExtraColor3,
        figmaColors = darkFc,
    )

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }
