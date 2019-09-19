package com.timeriver.gogank.fragment

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.timeriver.gogank.R

class MainPage3Fragment : Fragment() {

    private var intent: Intent? = null
    private lateinit var multicastLock: WifiManager.MulticastLock

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_page_3, container, false)
    }

    /**
     * 是否要增加一个UI上的交互？
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_page_3.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        //打开Multicast
        val wifiManager = requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        multicastLock = wifiManager.createMulticastLock("ipinfo")
        multicastLock.acquire()

        iv_shape.setOnClickListener {
            testCreateConnection()
        }
    }

    private fun testCreateConnection() {
        activity?.let {
            intent = Intent(it, SocketServerService::class.java)
            it.startService(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        intent?.let {
            activity?.stopService(it)
        }
        if (multicastLock.isHeld){
            multicastLock.release()
        }
    }

}