package com.example.uilibrary.util.scale

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.uilibrary.util.scale.getCurrentScreenWidth

@Composable fun Float.scaleFontSize(
    referenceWidth: Float = 430f,
    targetWidth: Float = getCurrentScreenWidth(),
): TextUnit {
    val scaleFactor = targetWidth / referenceWidth
    return (this * scaleFactor).sp
}
