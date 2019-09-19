package com.timeriver.gogank.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil(context: Context) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isNetworkActive: Boolean
         get() = connectivityManager.activeNetworkInfo.let { info ->
             info != null && info.isConnected
         }
}