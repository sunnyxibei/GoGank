package com.timeriver.gogank.data.service

import com.timeriver.gogank.data.model.AndroidNewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GankApiService {

    @GET("data/Android/{pageSize}/{pageNo}")
    suspend fun getAndroidNewsListAsync(
        @Path("pageNo") pageNo: Int,
        @Path("pageSize") pageSize: Int
    ): AndroidNewsResponse

}