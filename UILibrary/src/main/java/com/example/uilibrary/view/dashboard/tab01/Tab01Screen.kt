package com.example.uilibrary.view.dashboard.tab01

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.uilibrary.R
import com.example.uilibrary.coreui.theme.LocalCustomColorsPalette
import com.example.uilibrary.elements.appbar.AppBarDefault
import com.example.uilibrary.elements.banners.NoConnectionFound
import com.example.uilibrary.util.AppStatusBarColor
import com.example.uilibrary.util.scale.scaleHeight
import com.example.uilibrary.util.scale.scaleWidth
import com.gtn.basesdk.util.enums.ConnectionState

@Composable
fun Tab01Screen(viewModel: Tab01ViewModel = hiltViewModel()) {
    val baseNotificationState by viewModel.sharedState.value.baseNoficationState
        .collectAsState()

    AppStatusBarColor(
        statusBarColor = LocalCustomColorsPalette.current.figmaColors.Header0,
        navigationBarColor = LocalCustomColorsPalette.current.figmaColors.Background0,
    )

    Scaffold(
        topBar = {
            AppBarDefault(
                title = stringResource(R.string.device),
                bgColor = LocalCustomColorsPalette.current.figmaColors.Header0,
                fbColor = LocalCustomColorsPalette.current.figmaColors.Background10,
                elevated = false,
                bgBottomColor = LocalCustomColorsPalette.current.figmaColors.Background0,
            )
        },
        content = { paddingValues ->
            Column(
                modifier =
                    Modifier.padding(
                        start = 0f.scaleWidth(),
                        end = 0f.scaleWidth(),
                        top = 16f.scaleHeight(),
                        bottom = paddingValues.calculateBottomPadding(),
                    ),
            ) {
                if (baseNotificationState.networkConnectivityState == ConnectionState.DISCONNECTED) {
                    NoConnectionFound()
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start,
                        modifier =
                            Modifier
                                .background(color = LocalCustomColorsPalette.current.figmaColors.Background10)
                                .padding(top = 69f.scaleHeight(), bottom = 0f.scaleHeight())
                                .pointerInput(Unit) {
                                },
                    ) {
                        item {
                            Text(
                                "Abs"
                            )
                        }
                        item {
                        }
                        item {
                        }
                    }
                }
            }
        },
    )
}
