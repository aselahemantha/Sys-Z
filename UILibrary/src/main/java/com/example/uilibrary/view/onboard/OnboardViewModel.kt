package com.gtn.uilibrary.view.onboard

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basesdk.domain.usecase.auth.ConnectivityCheckerUseCase
import com.gtn.basesdk.util.enums.ConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class OnboardViewModel
    @Inject
    constructor(
        private val context: Context,
        private val connectivityCheckerUseCase: ConnectivityCheckerUseCase,
    ) : ViewModel() {
        val networkConnectivityStateIn = MutableStateFlow<ConnectionState>(ConnectionState.CONNECTING)
        val networkConnectivityState: StateFlow<ConnectionState> = networkConnectivityStateIn

        init {
            getConnectivity()
        }


        fun getConnectivity() {
            context.let {
                connectivityCheckerUseCase.let { usecaseOut ->
                    viewModelScope.launch {
                        connectivityCheckerUseCase.invoke(context).collect { state ->
                            networkConnectivityStateIn.emit(state)
                        }
                    }
                }
            }
        }

        fun openInternetSetting() {
            val intent =
                Intent(Settings.ACTION_SETTINGS).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

    }
