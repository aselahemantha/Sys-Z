package com.example.uilibrary.elements.popup.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

@Composable fun CustomSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    ) { snackbarData ->
        CustomSnackbar(
            action = { anyData ->
                (snackbarData.visuals as? CustomSnackbarVisuals)?.action?.let { it(anyData) }
            },
            actionTag = (snackbarData.visuals as? CustomSnackbarVisuals)?.actionTag ?: "",
            close = (snackbarData.visuals as? CustomSnackbarVisuals)?.close ?: {},
            severity = (snackbarData.visuals as? CustomSnackbarVisuals)?.severity ?: SnackbarSeverity.INFO,
            annotatedMessage = (snackbarData.visuals as? CustomSnackbarVisuals)?.annotatedMessage ?: buildAnnotatedString {},
        )
    }
}

data class CustomSnackbarVisuals(
    override val actionLabel: String? = "",
    val action: (Any) -> Unit = {},
    val actionTag: String = "",
    val close: () -> Unit = {},
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val message: String = "",
    override val withDismissAction: Boolean,
    val severity: SnackbarSeverity,
    var annotatedMessage: AnnotatedString = buildAnnotatedString {},
    var align: Alignment = Alignment.BottomCenter,
) : SnackbarVisuals
