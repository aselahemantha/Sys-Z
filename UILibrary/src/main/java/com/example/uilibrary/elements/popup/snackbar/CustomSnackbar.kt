package com.example.uilibrary.elements.popup.snackbar

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.uilibrary.R
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity.CONNECTIVITY_ISSUE
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity.ERROR
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity.INFO
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity.PRIMARY
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity.SUCCESS
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth
import noRippleClickable

@Composable fun CustomSnackbar(
    action: (Any) -> Unit = {},
    close: () -> Unit = {},
    severity: SnackbarSeverity = INFO,
    actionTag: String = "",
    annotatedMessage: AnnotatedString = buildAnnotatedString {},
) {
    val color =
        when (severity) {
            INFO -> LocalCustomColorsPalette.current.figmaColors.Grey90
            ERROR -> LocalCustomColorsPalette.current.figmaColors.Negative100
            SUCCESS -> LocalCustomColorsPalette.current.figmaColors.Success90
            PRIMARY -> LocalCustomColorsPalette.current.figmaColors.Background10
            CONNECTIVITY_ISSUE -> LocalCustomColorsPalette.current.figmaColors.White
        }

    val colorIco =
        when (severity) {
            INFO -> LocalCustomColorsPalette.current.figmaColors.Grey50
            ERROR -> LocalCustomColorsPalette.current.figmaColors.Negative0
            SUCCESS -> LocalCustomColorsPalette.current.figmaColors.Success50
            PRIMARY -> LocalCustomColorsPalette.current.figmaColors.Secondary50
            CONNECTIVITY_ISSUE -> LocalCustomColorsPalette.current.figmaColors.Negative0
        }

    val icon =
        when (severity) {
            INFO -> Icons.Outlined.Info
            ERROR -> Icons.Rounded.Error
            SUCCESS -> Icons.Rounded.CheckCircle
            PRIMARY -> Icons.Rounded.Info
            CONNECTIVITY_ISSUE -> ImageVector.vectorResource(id = R.drawable.device_health_splash)
        }

    ElevatedCard(
        modifier =
            Modifier
                .padding(start = 20f.scaleWidth(), bottom = 125f.scaleHeight(), end = 20f.scaleWidth())
                .border(1.dp, color.copy(alpha = 0.1f), shapes.medium),
        shape = shapes.small,
        elevation =
            androidx.compose.material3.CardDefaults
                .elevatedCardElevation(10.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = color.copy(alpha = 1f))
                    .padding(horizontal = 24f.scaleWidth(), vertical = 16f.scaleHeight()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorIco,
                modifier = Modifier.width(24f.scaleWidth()),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier =
                    Modifier.width(278f.scaleWidth())
                        .wrapContentHeight()
                        .padding(horizontal = 8f.scaleWidth()),
            ) {
                ClickableText(
                    text = annotatedMessage,
                    style =
                        TextStyle(
                            color = LocalCustomColorsPalette.current.figmaColors.Typography60,
                            fontSize = 14f.scaleFontSize(),
                            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                            lineHeight = 20f.scaleFontSize(),
                            letterSpacing = 0.25f.scaleFontSize(),
                            textDecoration = TextDecoration.None,
                            textAlign = TextAlign.Start,
                        ),
                    modifier = Modifier.width(278f.scaleWidth()),
                    onClick = { offset ->
                        if (actionTag.isNotEmpty()) {
                            annotatedMessage
                                .getStringAnnotations(tag = actionTag, start = offset, end = offset)
                                .firstOrNull()
                                ?.let { annotation ->
                                    action(Uri.parse(annotation.item))
                                }
                        }
                    },
                )
            }
            if (!severity.equals(SnackbarSeverity.CONNECTIVITY_ISSUE)) {
                Spacer(modifier = Modifier.width(4f.scaleWidth()))
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null,
                    tint = LocalCustomColorsPalette.current.figmaColors.Grey70,
                    modifier =
                        Modifier.size(24f.scaleWidth()).noRippleClickable {
                            close()
                        },
                )
            } else {
                Spacer(modifier = Modifier.width(24f.scaleWidth()))
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 120)
@Composable
private fun CustomSnackbarPreview(
    @PreviewParameter(SnackbarSeverityProvider::class) severity: SnackbarSeverity,
) {
    ComposeSnackbarsTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
            ) {
                CustomSnackbar(
                    severity = severity,
                    annotatedMessage =
                        buildAnnotatedString {
                            append("THIS SNACK BAR")
                        },
                )
            }
        }
    }
}
