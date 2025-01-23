package com.example.uilibrary.elements.banners

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uilibrary.R
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.elements.popup.snackbar.shapes
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth

@Composable
fun SlowConnection(
    isVisible: Boolean = false,
    title: String = "Slow Internet Connection!",
) {
    var color = LocalCustomColorsPalette.current.figmaColors.Background0

    // Auto-dismiss logic
//    LaunchedEffect(isVisible) {
//        if (isVisible) {
//            delay(2000)
//        }
//    }
    AnimatedVisibility(
        visible = isVisible,
        enter =
            slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 300),
            ),
        exit =
            slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(durationMillis = 300),
            ),
    ) {
        ElevatedCard(
            modifier =
                Modifier
                    .padding(start = 20f.scaleWidth(), end = 20f.scaleWidth(), top = 8f.scaleHeight())
                    .border(1.dp, color.copy(alpha = 0.1f), shapes.medium),
            shape = shapes.medium,
            elevation =
                androidx.compose.material3.CardDefaults
                    .elevatedCardElevation(10.dp),
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(80f.scaleHeight())
                        .background(color = color.copy(alpha = 1f))
                        .padding(horizontal = 24f.scaleWidth(), vertical = 16f.scaleHeight()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.device_health_splash),
                    contentDescription = null,
                    tint = LocalCustomColorsPalette.current.figmaColors.Negative0,
                    modifier = Modifier.size(24f.scaleWidth()),
                )
                Spacer(modifier = Modifier.size(8f.scaleWidth()))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier =
                        Modifier
                            .width(278f.scaleWidth())
                            .height(80f.scaleHeight()),
                ) {
                    Text(
                        text = title,
                        fontSize = 14f.scaleFontSize(),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        color = LocalCustomColorsPalette.current.figmaColors.Typography50,
                        textAlign = TextAlign.Left,
                    )
                    Text(
                        text = "Please check your internet settings",
                        fontSize = 14f.scaleFontSize(),
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        color = LocalCustomColorsPalette.current.figmaColors.Typography50,
                        textAlign = TextAlign.Left,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SlowConnectionPreview() {
    SlowConnection(true)
}
