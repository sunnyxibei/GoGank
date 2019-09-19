package com.timeriver.gogank.base

import com.ashokvarma.gander.GanderInterceptor
import com.timeriver.gogank.data.service.GankApiService
import com.timeriver.gogank.domain.GankRepository
import com.timeriver.gogank.util.NetworkUtil
import com.timeriver.gogank.viewmodel.AndroidNewsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val koinModule = module {

    single { NetworkUtil(get()) }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(GlobalNetworkErrorInterceptor(get()))
            .addNetworkInterceptor(GanderInterceptor(get()).showNotification(true))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(get())
            .build()
    }

    single<GankApiService> {
        get<Retrofit>().create()
    }

    single { GankRepository(get()) }

    viewModel { AndroidNewsViewModel(get()) }

}