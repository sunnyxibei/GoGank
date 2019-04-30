package com.timeriver.gogank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 使用Kotlin Coroutines替代RxJava实现异步请求
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Hello MainActivity!")
        val launch = GlobalScope.launch {
            println("Hello Kotlin Coroutines!")
        }

        runBlocking {
            launch.join()
        }
    }
}
