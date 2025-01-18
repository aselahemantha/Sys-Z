package com.example.uilibrary.view.onboard.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uilibrary.R
import com.example.uilibrary.banners.NoConnection
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.designSystem.uiComponent.TextComponent
import com.example.uilibrary.util.AppStatusbarColor
import com.example.uilibrary.util.scale.scaleFontSize
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth
import com.gtn.basesdk.util.enums.ConnectionState
import com.gtn.uilibrary.view.onboard.splash.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    val connectionState by viewModel.networkConnectivityState.collectAsState()
    var isTextFieldVisible by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    LaunchedEffect(key1 = viewModel, key2 = connectionState) {
        if (connectionState == ConnectionState.CONNECTED) {
            val fetchData = viewModel.initialFetchAndCacheConfiguration()
            if (fetchData.data == null) {
                textFieldValue = fetchData.message.toString()
                isTextFieldVisible = true
            } else {
                textFieldValue = fetchData.message.toString()
            }
            delay(350)
            viewModel.navigateToLogin()
        }
    }

    AppStatusbarColor(
        statusBarColor = LocalCustomColorsPalette.current.figmaColors.Background10,
        navigationBarColor = LocalCustomColorsPalette.current.figmaColors.Background10,
    )

    Box {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(LocalCustomColorsPalette.current.figmaColors.Background10),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(300f.scaleHeight()))

            Image(
                painter = painterResource(id = R.drawable.device_health_splash),
                contentDescription = "gtn logo",
                modifier =
                    Modifier
                        .width(296f.scaleWidth())
                        .height(133f.scaleHeight()),
            )

            Spacer(modifier = Modifier.height(396f.scaleHeight()))

            Row {
                TextComponent(
                    text = AnnotatedString("Abc"),
                    fontSize = 12f.scaleFontSize(),
                    fontColor = LocalCustomColorsPalette.current.figmaColors.Typography80,
                )
                Spacer(modifier = Modifier.width(4f.scaleWidth()))
                Image(
                    painter = painterResource(R.drawable.device_health_splash),
                    contentDescription = "GTN icon",
                    modifier =
                        Modifier
                            .width(16.dp)
                            .height(15.dp)
                )
            }
        }
        NoConnection(isVisible = connectionState == ConnectionState.DISCONNECTED)
    }
}
