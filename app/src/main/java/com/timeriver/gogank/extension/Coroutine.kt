package com.timeriver.gogank.extension

import com.timeriver.gogank.domain.exception.NetworkException
import com.timeriver.gogank.domain.exception.TokenExpiredException
import com.timeriver.gogank.domain.handler.BaseGlobalExceptionHandler


import kotlinx.coroutines.CoroutineScope

inline fun CoroutineScope.handleApiErrors(
    e: Exception,
    crossinline actions: (e: Exception) -> Unit
) {
    when (e) {
        is NetworkException -> {
            // handle Global network error
            BaseGlobalExceptionHandler.showNetworkDialog()
        }
        is TokenExpiredException -> {
            BaseGlobalExceptionHandler.handleTokenExpired()
        }
        else -> actions(e)
    }
}
