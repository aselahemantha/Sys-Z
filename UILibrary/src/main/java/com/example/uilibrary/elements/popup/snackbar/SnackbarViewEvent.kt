package com.example.uilibrary.elements.popup.snackbar

import androidx.compose.ui.Alignment
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import com.example.uilibrary.elements.popup.snackbar.SnackBarEventKey
import com.example.uilibrary.elements.popup.snackbar.SnackbarSeverity
import java.util.UUID

data class SnackbarViewEvent(
    val key: SnackBarEventKey = SnackBarEventKey.OTHER,
    val value: Any? = null,
    val actionTag: String = "",
    val action: (UUID, Any) -> Unit = { uuidData, anyData -> },
    val close: (UUID) -> Unit = {},
    val severity: SnackbarSeverity = SnackbarSeverity.INFO,
    val eventId: UUID = UUID.randomUUID(),
    val annotatedMessage: AnnotatedString = buildAnnotatedString {},
    val align: Alignment = Alignment.BottomCenter,
)
