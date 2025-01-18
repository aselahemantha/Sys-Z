package com.example.uilibrary.view.onboard.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp =
                    ObjectAnimator.ofFloat(
                        splashScreenView,
                        View.TRANSLATION_Y,
                        0f,
                        -splashScreenView.height.toFloat(),
                    )

                // Get the duration of the animated vector drawable.
                val animationDuration = splashScreenView.iconAnimationDuration
                // Get the start time of the animation.
                val animationStart = splashScreenView.iconAnimationStart
                // Calculate the remaining duration of the animation.
                val remainingDuration =
                    if (animationDuration != null && animationStart != null) {
                        (animationDuration - Duration.between(animationStart, Instant.now()))
                            .toMillis()
                            .coerceAtLeast(0L)
                    } else {
                        0L
                    }

                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = remainingDuration
                slideUp.doOnEnd { splashScreenView.remove() }
                slideUp.start()
            }

            SplashScreen()
        }

        lifecycleScope.launch {
            startActivity(
                Intent(this@SplashActivity, SplashActivity::class.java),
            )
            finish()
        }
    }
}
