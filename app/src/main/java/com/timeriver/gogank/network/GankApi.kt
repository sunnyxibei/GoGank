package com.timeriver.gogank.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GankApi {

    @GET("today")
    fun getTodayGank(): Deferred<GankBean>
}