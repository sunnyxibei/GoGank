package com.timeriver.gogank.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.timeriver.gogank.R
import com.timeriver.gogank.network.GankApiHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

/**
 * 使用Kotlin Coroutines替代RxJava实现异步请求
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
    }

    private fun initData() {
       runBlocking{
           println("Start Network Request")
           println(GankApiHelper.instance.getTodayGank().await().error)
           println("Finish Network Request")
       }
    }
}
