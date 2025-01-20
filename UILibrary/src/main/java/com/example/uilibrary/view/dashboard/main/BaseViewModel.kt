package com.example.uilibrary.view.dashboard.main

import android.content.Context
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.lifecycle.viewModelScope
import com.example.basesdk.domain.usecase.auth.ConnectivityCheckerUseCase
import com.example.uilibrary.elements.popup.snackbar.SnackBarEventKey
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity
import com.example.uilibrary.elements.popup.snackbar.SnackbarViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel() : BaseViewCoreModel() {
    init {
//        if (!profileInfo.value.isNullOrEmpty() && !profileInfo.value.userCurrency().isEmpty()) {
//            appCurrency(profileInfo.value.userCurrency())
//        } else {
//            institute.value?.let {
//                appCurrency(currency = if (it.info.currency.isNullOrEmpty()) "USD" else it.info.currency)
//            }
//        }

        viewModelScope.launch {
            DashBoardEvent.snackbarEventList.distinctUntilChanged().collect { data ->
                snackBarEvents.value
                    .toMutableList()
                    .apply {
                        add(data)
                    }.also {
                        snackBarLiveData.value = it
                    }
            }
        }
    }

    @Inject
    constructor(
        context: Context? = null,
        connectivityCheckerUseCase: ConnectivityCheckerUseCase? = null,
    ) : this() {
        context?.let {
            SHARED.connectivityCheckerUseCase ?: connectivityCheckerUseCase?.let { usecaseOut ->
                SHARED.connectivityCheckerUseCase = usecaseOut
                SHARED.connectivityCheckerUseCase?.let { usecaseIn ->
                    viewModelScope.launch {
                        connectivityCheckerUseCase.invoke(context).collect { state ->
                            updateBaseState(BaseNotification(networkConnectivityState = state))
                        }
                    }
                }
            }
        }

//        SHARED.userPrefUseCase ?: userPrefUseCase?.let { usecaseOut ->
//            SHARED.userPrefUseCase = usecaseOut
//            appLan(lan = usecaseOut.userLangPref(null) ?: "EN")
//        }
//
//        SHARED.instituteUseCase ?: instituteUseCase?.let { usecaseOut ->
//            SHARED.instituteUseCase = usecaseOut
//        }


    }



    // app locals




    fun addSnackbarEvent(
        key: SnackBarEventKey = SnackBarEventKey.OTHER,
        value: Any? = null,
        severity: SnackbarSeverity,
        actionTag: String = "",
        action: (UUID, Any) -> Unit = { uuidData, anyData -> },
        close: (UUID) -> Unit = {},
        eventId: UUID = UUID.randomUUID(),
        annotatedMessage: AnnotatedString = buildAnnotatedString {},
        align: Alignment = Alignment.BottomCenter,
    ) {
        viewModelScope.launch {
            DashBoardEvent.addSnachbarEvent(
                SnackbarViewEvent(
                    key = key,
                    value = value,
                    actionTag = actionTag,
                    action = { eventId, anyData -> action(eventId, anyData) },
                    close = {
                        close(eventId)
                    },
                    severity = severity,
                    annotatedMessage = annotatedMessage,
                    align = align,
                ),
            )
            delay(4000)
            baseSnackbarHostState.currentSnackbarData?.dismiss()
            removeSnackbarEvent(
                key = key,
                eventId = eventId,
            )
        }
    }

    fun removeSnackbarEvent(
        key: SnackBarEventKey = SnackBarEventKey.OTHER,
        eventId: UUID = UUID.randomUUID(),
        removeLast: Boolean = false,
    ) {
        viewModelScope.launch {
            if (removeLast) {
                if (snackBarEvents.value.isNotEmpty()) {
                    snackBarEvents.value
                        .toMutableList()
                        .apply {
                            removeLast()
                        }.also {
                            snackBarLiveData.value = it
                        }
                }
            } else {
                snackBarEvents.value
                    .toMutableList()
                    .apply {
                        removeIf { it.eventId == eventId && it.key == key }
                        snackBarEvents.value = this
                    }.also {
                        snackBarLiveData.value = it
                    }
            }
        }
    }

    fun updateBaseState(state: BaseNotification = BaseNotification()) {
        viewModelScope.launch {
            baseNoficationState.emit(
                baseNoficationState.value.copy(
                    isLoading = state.isLoading,
                    notifier = state.notifier,
                    apiErrorPrimary = state.apiErrorPrimary,
                    networkConnectivityState = state.networkConnectivityState,
                ),
            )
        }
    }
}
