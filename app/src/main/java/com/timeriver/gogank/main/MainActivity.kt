package com.timeriver.gogank.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.timeriver.gogank.R
import com.timeriver.gogank.network.GankApiHelper
import com.timeriver.gogank.network.GankBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
        runBlocking {
            showLoading()
            var gankBean: GankBean? = null
            withContext(Dispatchers.IO) {
                println("Start Network Request")
                gankBean = GankApiHelper.instance.getTodayGank().await()
                println("Finish Network Request")
            }
            hideLoading()
            gankBean?.let {
                tv_list.layoutManager = LinearLayoutManager(this@MainActivity)
                tv_list.adapter = MainAdapter(it)
            }
        }
    }

    private fun showLoading() {
        Snackbar.make(tv_list, "loading", Snackbar.LENGTH_SHORT).show()
    }

    private fun hideLoading() {
        Snackbar.make(tv_list, "finish", Snackbar.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }
}
