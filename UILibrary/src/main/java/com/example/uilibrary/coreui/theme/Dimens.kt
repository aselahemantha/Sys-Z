package com.example.uilibrary.coreui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val tinyPadding: Dp = 2.dp,
    val smallPadding: Dp = 4.dp,
    val mediumPadding: Dp = 8.dp,
    val largePadding: Dp = 16.dp,
    val extraLargePadding: Dp = 24.dp,
    val smallMargin: Dp = 4.dp,
    val mediumMargin: Dp = 8.dp,
    val largeMargin: Dp = 16.dp,
    val smallSpacer: Dp = 4.dp,
    val mediumSpacer: Dp = 8.dp,
    val largeSpacer: Dp = 16.dp,
    val smallCornerRadius: Dp = 2.dp,
    val mediumCornerRadius: Dp = 4.dp,
    val largeCornerRadius: Dp = 8.dp,
    val smallButton: ButtonDimensions,
    val mediumButton: ButtonDimensions,
    val largeButton: ButtonDimensions,
)

data class ButtonDimensions(
    val height: Dp,
    val padding: Dp,
    val margin: Dp,
    val cornerRadius: Dp,
)

// Dimensions for Small screens (e.g., small phones)
val SmallScreenDimensions =
    Dimens(
        tinyPadding = 2.dp,
        smallPadding = 4.dp,
        mediumPadding = 8.dp,
        largePadding = 16.dp,
        extraLargePadding = 24.dp,
        smallMargin = 4.dp,
        mediumMargin = 8.dp,
        largeMargin = 16.dp,
        smallSpacer = 4.dp,
        mediumSpacer = 8.dp,
        largeSpacer = 16.dp,
        smallCornerRadius = 2.dp,
        mediumCornerRadius = 4.dp,
        largeCornerRadius = 8.dp,
        smallButton =
            ButtonDimensions(
                height = 32.dp,
                padding = 4.dp,
                margin = 4.dp,
                cornerRadius = 2.dp,
            ),
        mediumButton =
            ButtonDimensions(
                height = 40.dp,
                padding = 6.dp,
                margin = 6.dp,
                cornerRadius = 4.dp,
            ),
        largeButton =
            ButtonDimensions(
                height = 44.dp,
                padding = 8.dp,
                margin = 8.dp,
                cornerRadius = 6.dp,
            ),
    )

// Dimensions for Medium screens (e.g., regular phones)
val MediumScreenDimensions =
    Dimens(
        tinyPadding = 4.dp,
        smallPadding = 8.dp,
        mediumPadding = 16.dp,
        largePadding = 24.dp,
        extraLargePadding = 32.dp,
        smallMargin = 8.dp,
        mediumMargin = 16.dp,
        largeMargin = 24.dp,
        smallSpacer = 8.dp,
        mediumSpacer = 16.dp,
        largeSpacer = 24.dp,
        smallCornerRadius = 4.dp,
        mediumCornerRadius = 8.dp,
        largeCornerRadius = 16.dp,
        smallButton =
            ButtonDimensions(
                height = 36.dp,
                padding = 8.dp,
                margin = 8.dp,
                cornerRadius = 4.dp,
            ),
        mediumButton =
            ButtonDimensions(
                height = 44.dp,
                padding = 12.dp,
                margin = 12.dp,
                cornerRadius = 6.dp,
            ),
        largeButton =
            ButtonDimensions(
                height = 48.dp,
                padding = 16.dp,
                margin = 16.dp,
                cornerRadius = 8.dp,
            ),
    )

// Dimensions for Large screens (e.g., tablets or large phones)
val LargeScreenDimensions =
    Dimens(
        tinyPadding = 6.dp,
        smallPadding = 12.dp,
        mediumPadding = 24.dp,
        largePadding = 32.dp,
        extraLargePadding = 40.dp,
        smallMargin = 12.dp,
        mediumMargin = 24.dp,
        largeMargin = 32.dp,
        smallSpacer = 12.dp,
        mediumSpacer = 24.dp,
        largeSpacer = 32.dp,
        smallCornerRadius = 6.dp,
        mediumCornerRadius = 12.dp,
        largeCornerRadius = 24.dp,
        smallButton =
            ButtonDimensions(
                height = 40.dp,
                padding = 6.dp,
                margin = 6.dp,
                cornerRadius = 4.dp,
            ),
        mediumButton =
            ButtonDimensions(
                height = 48.dp,
                padding = 10.dp,
                margin = 10.dp,
                cornerRadius = 6.dp,
            ),
        largeButton =
            ButtonDimensions(
                height = 56.dp,
                padding = 12.dp,
                margin = 12.dp,
                cornerRadius = 8.dp,
            ),
    )
