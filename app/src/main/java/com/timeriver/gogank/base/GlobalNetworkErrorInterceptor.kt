package com.timeriver.gogank.base

import com.timeriver.gogank.domain.exception.NetworkException
import com.timeriver.gogank.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class GlobalNetworkErrorInterceptor(private val networkUtil: NetworkUtil) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (networkUtil.isNetworkActive || request.cacheControl().onlyIfCached()) {
            return chain.proceed(request)
        } else {
            throw NetworkException()
        }
    }
}