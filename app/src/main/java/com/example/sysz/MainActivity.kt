package com.example.sysz

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import com.example.starter.BuildConfig
import com.example.uilibrary.coreui.theme.AuthenticationAppTheme
import com.example.uilibrary.util.enums.AppTheme
import com.example.uilibrary.view.onboard.splash.SplashActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.gtn.uilibrary.view.onboard.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private val appTheme = mutableStateOf(AppTheme.SYSTEM)

    override fun onCreate(savedInstanceState: Bundle?) {
        startActivity(Intent(this@MainActivity, SplashActivity::class.java))
        super.onCreate(savedInstanceState)

        // Disable firebase analytics for debug mode
        if (BuildConfig.DEBUG) {
            FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(false)
        }


        installSplashScreen().setKeepOnScreenCondition(viewModel.isLoading.value)
        createNotificationChannel()

        setContent {
            appTheme.value = AppTheme.fromCode(AppTheme.SYSTEM.code)
            AuthenticationAppTheme(appTheme = appTheme.value) {
                if (intent.hasExtra("messageTarget")) {
                    val targetView = intent.getStringExtra("messageTarget")
                    MainScreen(
                        targetView,
                        theme = appTheme.value,
                        onTheme = {
                            appTheme.value = it
                        },
                    )
                } else {
                    MainScreen(
                        null,
                        theme = appTheme.value,
                        onTheme = {
                            appTheme.value = it
                        },
                    )
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MainScreen(
        targetView: String?,
        theme: AppTheme = AppTheme.LIGHT,
        onTheme: (AppTheme) -> Unit,
    ) {
        val density = LocalDensity.current
        val customDensity = Density(density.density, 1.0f)
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {},
            content = {
                CompositionLocalProvider(LocalDensity provides customDensity) {
                    Box(
                        modifier =
                        Modifier
                            .pointerInput(Unit) {
                                // detectTapGestures(onTap = { focusManager.clearFocus() }) // comment for anr issue
                            },
                    ) {
                        Text(
                            "Hello Andorid"
                        )
                    }
                }
            },
        )
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        // Important to store the new intent
        if (intent.hasExtra("messageTarget")) {
            val targetView = intent.getStringExtra("messageTarget")
//            HandleNotification().handleNotification(targetView)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Firebase Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "firebase_channel_id"
    }
}