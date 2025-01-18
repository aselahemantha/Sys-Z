package com.example.uilibrary.di

import androidx.core.splashscreen.SplashScreen
import com.example.uilibrary.common.analytics.AnalyticsHelper
import com.example.uilibrary.util.AppNavigator
import com.example.uilibrary.util.AppNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppNavigator(analyticsHelper: AnalyticsHelper): AppNavigator {
        return AppNavigatorImpl(analyticsHelper = analyticsHelper)
    }

    @Provides
    @Singleton
    fun provideKeepOnScreenCondition(): SplashScreen.KeepOnScreenCondition {
        // Provide the implementation or condition for KeepOnScreenCondition
        return SplashScreen.KeepOnScreenCondition { true }
    }
}
