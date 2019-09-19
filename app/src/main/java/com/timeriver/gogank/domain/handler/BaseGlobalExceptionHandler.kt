package com.timeriver.gogank.domain.handler

import io.reactivex.subjects.PublishSubject

object BaseGlobalExceptionHandler {
    val networkFailureSubject = PublishSubject.create<Boolean>()
    val tokenExpiredSubject = PublishSubject.create<Boolean>()

    fun showNetworkDialog() {
        networkFailureSubject.onNext(true)
    }

    fun handleTokenExpired() {
        tokenExpiredSubject.onNext(true)
    }
}

