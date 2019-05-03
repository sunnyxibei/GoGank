package com.timeriver.gogank.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GankApiHelper private constructor() {

    companion object {
        val instance by lazy { GankApiHelper() }
    }

    private val mGankApi: GankApi

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(StethoInterceptor())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        mGankApi = retrofit.create(GankApi::class.java)
    }

    fun getTodayGank(): Deferred<GankBean> {
        return mGankApi.getTodayGank()
    }
}