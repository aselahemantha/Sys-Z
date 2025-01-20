package com.example.uilibrary.view.dashboard.main

import com.gtn.basesdk.util.enums.ConnectionState
import com.gtn.basesdk.util.enums.PulseState

data class BaseNotification(
    val userId: String = "",
    var isLoading: Boolean = false,
    var notifier: NotifierType = NotifierType.NONE,
    var apiErrorPrimary: NotifierType = NotifierType.NONE,
    var apiErrorSecondary: NotifierType = NotifierType.NONE,
    var networkConnectivityState: ConnectionState = ConnectionState.CONNECTING,
    var priceConnectionState: ConnectionState = ConnectionState.DISCONNECTED,
    var tradeConnectionState: ConnectionState = ConnectionState.DISCONNECTED,
    var pricePulseState: PulseState = PulseState.DOWN,
    var tradePulseState: PulseState = PulseState.DOWN,
)

enum class NotifierType { CHANGE, LOADING, SUCCESS, FAIL, NONE }
