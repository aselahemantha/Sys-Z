package com.example.uilibrary.elements.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.util.AppStatusBarColor
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth
import noRippleClickable

@Preview
@Composable
fun AppBarExpandable(
    onBack: (() -> Unit)? = null,
    icon: ImageVector = Icons.Default.MoreVert,
    bgColor: Color = LocalCustomColorsPalette.current.figmaColors.Primary0,
    fbColor: Color = LocalCustomColorsPalette.current.figmaColors.Typography90,
    elevated: Boolean = true,
    expanded: Boolean = true,
    title: @Composable () -> Unit = {},
    actions: List<@Composable () -> Unit> = listOf(),
    content: @Composable () -> Unit = {},
) {
    AppStatusBarColor(
        statusBarColor = bgColor,
        navigationBarColor = bgColor,
    )
    Surface(
        shadowElevation = if (elevated) 10.dp else 0.dp,
        modifier =
            Modifier
                .fillMaxWidth()
                // .height(61f.scaleHeight())
                .wrapContentHeight()
                .background(bgColor),
    ) {
        Column {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(61f.scaleHeight())
                        .background(bgColor),
            ) {
                Row(
                    modifier =
                        Modifier
                            .padding(start = 20f.scaleWidth(), end = 20f.scaleWidth(), top = 6f.scaleHeight(), bottom = 11f.scaleHeight())
                            .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(24f.scaleWidth())
                                .noRippleClickable {
                                    if (onBack != null) {
                                        onBack()
                                    }
                                },
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (onBack != null) {
                            Icon(
                                modifier =
                                    Modifier
                                        .size(24f.scaleWidth()),
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = fbColor,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10f.scaleWidth()))
                    Box(
                        modifier =
                            Modifier
                                .weight(1f)
                    ) {
                        title()
                    }
                    Spacer(modifier = Modifier.width(10f.scaleWidth()))
                    if (actions.isNotEmpty()) {
                        actions.onEach { action ->
                            action()
                        }
                    } else {
                        Box(
                            modifier =
                                Modifier
                                    .size(24f.scaleWidth())
                                    .noRippleClickable {},
                            contentAlignment = Alignment.CenterEnd,
                        ) {
                        }
                    }
                }
            }
            if (expanded) {
                content()
            }
        }
    }
}
