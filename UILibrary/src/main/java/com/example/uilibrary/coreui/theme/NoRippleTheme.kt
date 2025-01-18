package com.example.uilibrary.coreui.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = androidx.compose.ui.graphics.Color.Transparent

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f, 0.0f, 0.0f, 0.0f)
}
