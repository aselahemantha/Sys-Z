package com.example.uilibrary.elements.popup.snackbar

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

enum class SnackbarSeverity {
    INFO,
    ERROR,
    SUCCESS,
    PRIMARY,
    CONNECTIVITY_ISSUE,
}

class SnackbarSeverityProvider : PreviewParameterProvider<SnackbarSeverity> {
    override val values: Sequence<SnackbarSeverity>
        get() = SnackbarSeverity.values().asSequence()
}
