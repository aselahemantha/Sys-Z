package com.example.basesdk.util

import com.example.basesdk.R


abstract class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val messageRes: Int = 1,
    val isPlatformError: Boolean = false,
) {
    class Success<T>(
        data: T,
    ) : Resource<T>(data)

    class Loading<T>(
        data: T? = null,
    ) : Resource<T>(data)

    class Error<T>(
        message: String,
        data: T? = null,
        messageRes: Int = 1,
        isPlatformError: Boolean = false,
    ) : Resource<T>(data, message, messageRes, isPlatformError)
}
