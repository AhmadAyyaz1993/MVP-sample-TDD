package evonative.app.com.sadapaytest

import java.net.InetAddress
import java.net.UnknownHostException


class utils {
    companion object{
        fun isInternetAvailable(): Boolean {
            try {
                val address: InetAddress = InetAddress.getByName("www.google.com")
                return !address.equals("")
            } catch (e: UnknownHostException) {
                // Log error
                return false
            }
            return false
        }

    }
}