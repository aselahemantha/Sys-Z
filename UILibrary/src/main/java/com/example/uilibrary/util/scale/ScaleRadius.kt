package com.example.uilibrary.util.scale

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.uilibrary.util.scale.getCurrentScreenWidth

@Composable fun Float.scaleRadius(
    referenceWidth: Float = 430f,
    targetWidth: Float = getCurrentScreenWidth(),
): Dp {
    val scaleFactor = targetWidth / referenceWidth
    return (this * scaleFactor).dp
}
