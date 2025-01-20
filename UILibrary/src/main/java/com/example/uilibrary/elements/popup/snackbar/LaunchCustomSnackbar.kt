package com.example.uilibrary.elements.popup.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import java.util.UUID

@Composable
fun LaunchCustomSnackbar(
    key: Any?,
    snackbarHostState: SnackbarHostState,
    action: (UUID) -> Unit = {},
    close: (UUID) -> Unit = {},
    onTimeOut: (UUID) -> Unit = {},
    severity: SnackbarSeverity,
    eventId: UUID = UUID.randomUUID(),
) {
    LaunchedEffect(key) {
        snackbarHostState.showSnackbar(
            visuals =
                CustomSnackbarVisuals(
                    action = { action(eventId) },
                    close = { close(eventId) },
                    duration = SnackbarDuration.Short,
                    withDismissAction = false,
                    severity = severity,
                ),
        )
        delay(4000L)
        onTimeOut(eventId)
    }
}
