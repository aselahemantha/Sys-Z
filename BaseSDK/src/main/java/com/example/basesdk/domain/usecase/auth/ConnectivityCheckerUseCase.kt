package com.example.basesdk.domain.usecase.auth

import android.content.Context
import com.example.basesdk.util.ConnectivityChecker
import com.gtn.basesdk.util.enums.ConnectionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class ConnectivityCheckerUseCase() {
    operator fun invoke(context: Context): Flow<ConnectionState> =
        ConnectivityChecker(context)
            .connectivityStatus
            .catch { emit(ConnectionState.ERROR) }
            // Emit ERROR on exception
            .flowOn(Dispatchers.IO)
}
