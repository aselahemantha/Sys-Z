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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.util.AppStatusBarColor
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth
import noRippleClickable

@Preview
@Composable
fun AppBarDefault(
    title: String = "Title",
    onActionPrefix: (() -> Unit)? = null,
    onActionSuffix: (() -> Unit)? = null,
    iconPrefix: ImageVector = Icons.Default.ArrowBack,
    iconSuffix: ImageVector = Icons.Default.MoreVert,
    bgColor: Color = LocalCustomColorsPalette.current.figmaColors.Primary0,
    bgBottomColor: Color? = null,
    fbColor: Color = LocalCustomColorsPalette.current.figmaColors.Typography90,
    elevated: Boolean = true,
    content: @Composable () -> Unit = {},
) {
    AppStatusBarColor(
        statusBarColor = bgColor,
        navigationBarColor = bgBottomColor ?: bgColor,
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
                            .padding(horizontal = 20f.scaleWidth(), vertical = 18f.scaleHeight())
                            .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(24f.scaleWidth())
                                .noRippleClickable {
                                    if (onActionPrefix != null) {
                                        onActionPrefix()
                                    }
                                },
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (onActionPrefix != null) {
                            Icon(
                                modifier =
                                    Modifier
                                        .size(24f.scaleWidth()),
                                imageVector = iconPrefix,
                                contentDescription = "Back",
                                tint = fbColor,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10f.scaleWidth()))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier =
                            Modifier
                                .weight(1f)
                    ) {
                        Text(
                            text = title,
                            style =
                                MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 20f.scaleFontSize(),
                                    color = fbColor,
                                ),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.width(10f.scaleWidth()))
                    Box(
                        modifier =
                            Modifier
                                .size(24f.scaleWidth())
                                .noRippleClickable {
                                    if (onActionSuffix != null) {
                                        onActionSuffix()
                                    }
                                },
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        if (onActionSuffix != null) {
                            Icon(
                                modifier =
                                    Modifier
                                        .size(24f.scaleWidth()),
                                imageVector = iconSuffix,
                                contentDescription = "More",
                                tint = fbColor,
                            )
                        }
                    }
                }
            }
            content()
        }
    }
}
