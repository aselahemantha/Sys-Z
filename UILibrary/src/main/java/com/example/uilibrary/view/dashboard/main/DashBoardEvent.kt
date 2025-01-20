package com.example.uilibrary.view.dashboard.main

import com.example.uilibrary.elements.popup.snackbar.SnackbarViewEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object DashBoardEvent {
    private val _snackbarEventList = MutableSharedFlow<SnackbarViewEvent>(replay = 1)
    val snackbarEventList: SharedFlow<SnackbarViewEvent> get() = _snackbarEventList

    suspend fun addSnachbarEvent(event: SnackbarViewEvent) {
        _snackbarEventList.emit(event)
    }
}
