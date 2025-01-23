package com.example.uilibrary.elements.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.uilibrary.R
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth

@Preview
@Composable
fun BrandLineHorizontal(
    modifier: Modifier =
        Modifier
            .height(56f.scaleHeight()),
) {
    Row(
        modifier =
            modifier
                .padding(horizontal = 20f.scaleWidth(), vertical = 20f.scaleHeight())
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(id = R.string.bottom_line),
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 12f.scaleFontSize(),
            color = LocalCustomColorsPalette.current.figmaColors.Typography80,
        )
        Spacer(modifier = Modifier.width(2f.scaleWidth()))
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.sysz_splash_1),
            contentDescription = "GTN logo",
            modifier =
                Modifier
                    .size(16f.scaleWidth()),
            // .offset(y = (-4).dp),
        )
    }
}
