package com.example.uilibrary.designSystem.uiComponent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.uilibrary.R
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.util.scale.scaleFontSize

@Composable
fun TextComponent(
    modifier: Modifier = Modifier,
    text: AnnotatedString = AnnotatedString(""),
    fontSize: TextUnit = 14f.scaleFontSize(),
    fontFamily: FontFamily = FontFamily(Font(R.font.montserrat_medium)),
    fontWeight: FontWeight = FontWeight.Medium,
    fontColor: Color = LocalCustomColorsPalette.current.figmaColors.White,
    textAlign: TextAlign = TextAlign.Left,
    letterSpacing: TextUnit = 0.sp,
    onTap: () -> Unit = {},
) {
    Text(
        text = text,
        style =
            TextStyle(
                fontSize = fontSize,
                fontFamily = fontFamily,
                fontWeight = fontWeight,
                color = fontColor,
                textAlign = textAlign,
            ),
        letterSpacing = letterSpacing,
        modifier = modifier,
    )
}
