package com.example.uilibrary.elements.banners

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uilibrary.R
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth

@Composable
fun NoConnectionFound(
    onRetry: () -> Unit = {},
    titleName: String = "",
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(30f.scaleHeight()))

        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 140f.scaleHeight()),
        ) {
            Column(
                modifier =
                    Modifier
                        .background(Color.Transparent)
                        .width(439f.scaleWidth()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val image = painterResource(id = R.drawable.no_internet_connection)
                Image(
                    painter = image,
                    contentDescription = "No Internet Connection",
                    modifier =
                        Modifier
                            .width(120f.scaleWidth())
                            .height(69.4f.scaleHeight()),
                )

                Spacer(modifier = Modifier.height(24f.scaleHeight()))
                Box(modifier = Modifier.width(257f.scaleWidth())) {
                    Text(
                        text = "No Internet Connection Found",
                        fontSize = 16f.scaleFontSize(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        color = LocalCustomColorsPalette.current.figmaColors.Typography50,
                        textAlign = TextAlign.Center,
                    )
                }

                Spacer(modifier = Modifier.height(16f.scaleHeight()))

                Text(
                    text = "Please check your wi-fi or cellular\nconnection and try again.",
                    fontSize = 14f.scaleFontSize(),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                    color = LocalCustomColorsPalette.current.figmaColors.Typography50,
                    modifier = Modifier.padding(horizontal = 16f.scaleWidth()),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(24f.scaleHeight()))

                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(LocalCustomColorsPalette.current.figmaColors.Primary0),
                    shape = RoundedCornerShape(4.dp),
                    modifier =
                        Modifier
                            .width(187f.scaleWidth())
                            .height(44f.scaleHeight()),
                ) {
                    Text(
                        text = "Try Again",
                        fontSize = 14f.scaleFontSize(),
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        color = LocalCustomColorsPalette.current.figmaColors.Background0,
                        modifier = Modifier.padding(horizontal = 16f.scaleWidth()),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoConnectionFoundPreview() {
    NoConnectionFound(
        onRetry = { /* Preview action */ },
        titleName = "Network Issue",
    )
}
