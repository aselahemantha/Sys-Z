package com.example.uilibrary.view.dashboard.main

import com.gtn.basesdk.util.enums.ConnectionState
import com.example.basesdk.util.enums.AppState

data class BaseNotification(
    var isLoading: Boolean = false,
    var notifier: NotifierType = NotifierType.NONE,
    var apiErrorPrimary: NotifierType = NotifierType.NONE,
    var apiErrorSecondary: NotifierType = NotifierType.NONE,
    var networkConnectivityState: ConnectionState = ConnectionState.CONNECTING,
)

enum class NotifierType { CHANGE, LOADING, SUCCESS, FAIL, NONE }
