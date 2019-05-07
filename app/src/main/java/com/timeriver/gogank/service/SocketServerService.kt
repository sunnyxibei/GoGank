package com.timeriver.gogank.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.io.IOException
import java.net.*


class SocketServerService : Service() {

    companion object {
        private const val TAG = "SocketServerService"
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Thread { startSocketServer() }.start()
    }

    /**
     * 打开SocketServer
     */
    private fun startSocketServer() {
        val server = ServerSocket(0)
        val localPort = server.localPort
        val localHostIp = getLocalHostIp()
        Log.d(TAG, "localHostIp = $localHostIp")
        Log.d(TAG, "localPort = $localPort")
        //只传递端口号码

        //发送UDP广播，告知学生客户端，Server的IP & Port
//        val data: String = "{\n" +
//                "    \"host\":\"" + localHostIp + "\",\n" +
//                "    \"port\":\"" + localPort + "\"\n" +
//                "}"
        val data = localPort.toString()
        UdpBroadCast(data).start()

        while (true) {
            val accept = server.accept()
            if (accept != null) {

            }
        }
    }

    /* 发送udp多播 */
    private inner class UdpBroadCast(dataString: String) : Thread() {
        internal var sender: MulticastSocket? = null
        internal var packet: DatagramPacket? = null
        internal var group: InetAddress? = null

        internal var data = dataString.toByteArray()

        override fun run() {
            try {
                sender = MulticastSocket(6666)
                group = InetAddress.getByName("239.0.0.1")

                packet = DatagramPacket(data, data.size, group, 6666)
                sender?.send(packet)
                Log.d(TAG, "send Multicast")
                Log.d(TAG, "data.size  = ${data.size}")
                sender?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getLocalHostIp(): String {
        val ipaddress = ""
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            // 遍历所用的网络接口
            while (en.hasMoreElements()) {
                val nif = en.nextElement()// 得到每一个网络接口绑定的所有ip
                val inet = nif.inetAddresses
                // 遍历每一个接口绑定的所有ip
                while (inet.hasMoreElements()) {
                    val ip = inet.nextElement()
                    if (!ip.isLoopbackAddress && isIPv4Address(ip.hostAddress)) {
                        return ip.hostAddress
                    }
                }
            }
        } catch (e: SocketException) {
            Log.e(TAG, "fail to get ipv4 address")
            e.printStackTrace()
        }
        return ipaddress
    }

    private fun isIPv4Address(inputString: String): Boolean {
        val numbers = inputString.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (numbers.size != 4)
            return false
        for (number in numbers) {
            if (number.isEmpty())
                return false
            try {
                val value = Integer.parseInt(number)
                if (value < 0 || value > 255)
                    return false
            } catch (e: NumberFormatException) {
                return false
            }
        }
        return true
    }

}