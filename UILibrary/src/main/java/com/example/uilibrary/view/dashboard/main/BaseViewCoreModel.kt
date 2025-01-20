package com.example.uilibrary.view.dashboard.main

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basesdk.domain.model.InstitutionConfig
import com.example.basesdk.domain.usecase.auth.ConnectivityCheckerUseCase
import com.example.uilibrary.elements.popup.snackbar.SnackbarViewEvent
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewCoreModel : ViewModel() {
    protected val snackBarEvents: MutableState<List<SnackbarViewEvent>> = mutableStateOf(mutableListOf())
    protected val snackBarLiveData: MutableLiveData<List<SnackbarViewEvent>> = MutableLiveData(snackBarEvents.value)
    val snackBarEventState: LiveData<List<SnackbarViewEvent>> = snackBarLiveData
    val baseSnackbarHostState = SnackbarHostState()

    companion object SHARED {
        // base values
        val isWelcomePrompted = MutableStateFlow(true)
        var isInitialized: Boolean = false
        var isInitializedDashboard: Boolean = false
        var isTabo1Initialized: Boolean = false
        var isTabo2Initialized: Boolean = false
        var isTab02SubTab: Int = 0
        var isTabo3Initialized: Boolean = false
        var isTabo4Initialized: Boolean = false
        var isTabo5Initialized: Boolean = false
        var institutionConfig: InstitutionConfig? = null
        var isSPInitialized: Boolean = false

        var profileInfo = mutableStateOf("")

        // usecases
        var connectivityCheckerUseCase: ConnectivityCheckerUseCase? = null


        // var orderUseCase: OrderUseCase? = null

        // flows
        val baseNoficationState = MutableStateFlow<BaseNotification>(BaseNotification())

    }

    var sharedState = mutableStateOf(SHARED)

    fun resetBaseViewModel(): Boolean {
        isInitializedDashboard = false
        isInitialized = false
        isTabo1Initialized = false
        isSPInitialized = false
        isTabo2Initialized = false
        isTabo3Initialized = false
        isTabo4Initialized = false
        isTabo5Initialized = false

        // reset usecase
        connectivityCheckerUseCase = null

        // reset acc

        // reset other data

        return true
    }
}
