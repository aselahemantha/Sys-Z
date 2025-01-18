package com.example.uilibrary.util.scale

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable fun getStatusBar(): Dp {
    return WindowInsets.statusBars.getTop(LocalDensity.current).dp
}

@Composable fun getBottomBar(): Dp {
    return WindowInsets.navigationBars.getBottom(LocalDensity.current).dp
}

@Composable fun getSystemBarTop(): Dp {
    return WindowInsets.statusBars.getTop(LocalDensity.current).dp
}

@Composable fun getSystemBarBottom(): Dp {
    return WindowInsets.navigationBars.getBottom(LocalDensity.current).dp
}
