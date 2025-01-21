package com.example.uilibrary.view.onboard.splash

import android.content.Context
import androidx.core.splashscreen.SplashScreen
import com.example.basesdk.domain.model.InstitutionConfig
import com.example.basesdk.domain.usecase.auth.ConnectivityCheckerUseCase
import com.example.basesdk.domain.usecase.institute.InitialFetchUseCase
import com.example.basesdk.util.Resource
import com.example.uilibrary.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.FirebasePerformance
import com.example.uilibrary.coreui.NavigationItem
import com.example.uilibrary.util.AppNavigator
import com.gtn.uilibrary.view.onboard.OnboardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        private val appNavigator: AppNavigator,
        private val connectivityCheckerUseCase: ConnectivityCheckerUseCase,
        private val initialFetchUseCase: InitialFetchUseCase,
        private val context: Context,
    ) : OnboardViewModel(context, connectivityCheckerUseCase) {
        private var _isLoading: MutableStateFlow<SplashScreen.KeepOnScreenCondition> =
            MutableStateFlow(SplashScreen.KeepOnScreenCondition { false })
        val isLoading = _isLoading.asStateFlow()

//        suspend fun initialFetchAndCacheConfiguration(): Resource<InstitutionConfig?> {
//            if (BuildConfig.DEBUG) {
//                FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
//                FirebasePerformance.getInstance().isPerformanceCollectionEnabled = false
//            }
//            val result = MutableStateFlow(initialFetchUseCase.execute()).asStateFlow()
//            _isLoading.emit(SplashScreen.KeepOnScreenCondition { false })
//            return result.value
//        }

        fun navigateToLogin() {
            appNavigator.tryNavigateTo(
                NavigationItem.Tab01(),
                inclusive = true,
            )
        }
    }
