package com.example.uilibrary.view.dashboard.tab01.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleRadius
import com.example.uilibrary.util.scale.scaleWidth

@Preview(showBackground = false)
@Composable
fun DeviceInformation() {
    Row(
        modifier =
            Modifier
                .width(80f.scaleWidth())
//            .wrapContentHeight()
                .height(60f.scaleHeight())
                .border(
                    width = 0.1f.scaleWidth(),
                    color = LocalCustomColorsPalette.current.figmaColors.Background10,
                    shape = RoundedCornerShape(5f.scaleRadius()),
                ),
    ) {
        Row(
            modifier =
                Modifier
                    .width(80f.scaleWidth())
//            .wrapContentHeight()
                    .height(30f.scaleHeight())
                    .border(
                        width = 0.1f.scaleWidth(),
                        color = LocalCustomColorsPalette.current.figmaColors.Background10,
                        shape = RoundedCornerShape(5f.scaleRadius()),
                    ),
        ) {
            Column(
                modifier =
                    Modifier
                        .width(20f.scaleWidth())
                        .height(30f.scaleHeight())
                        .border(
                            width = 0.1f.scaleWidth(),
                            color = LocalCustomColorsPalette.current.figmaColors.Background10,
                            shape = RoundedCornerShape(5f.scaleRadius()),
                        ),
            ) {
            }
            Column(
                modifier =
                    Modifier
                        .width(60f.scaleWidth())
//            .wrapContentHeight()
                        .height(30f.scaleHeight())
                        .border(
                            width = 0.1f.scaleWidth(),
                            color = LocalCustomColorsPalette.current.figmaColors.Background10,
                            shape = RoundedCornerShape(5f.scaleRadius()),
                        ),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(15f.scaleHeight())
                            .border(
                                width = 0.1f.scaleWidth(),
                                color = LocalCustomColorsPalette.current.figmaColors.Background10,
                                shape = RoundedCornerShape(5f.scaleRadius()),
                            ),
                ) {
                }
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(15f.scaleHeight())
                            .border(
                                width = 0.1f.scaleWidth(),
                                color = LocalCustomColorsPalette.current.figmaColors.Background10,
                                shape = RoundedCornerShape(5f.scaleRadius()),
                            ),
                ) {
                }
            }
        }
        HorizontalDivider(
            thickness = 2f.scaleWidth(),
            color = LocalCustomColorsPalette.current.figmaColors.Background10,
        )
    }
}
