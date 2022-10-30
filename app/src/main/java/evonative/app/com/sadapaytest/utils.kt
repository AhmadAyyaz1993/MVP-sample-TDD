package evonative.app.com.sadapaytest

import java.net.InetAddress
import java.net.UnknownHostException
import java.util.concurrent.*


class utils {
    companion object{
        fun internetConnectionAvailable(timeOut: Int): Boolean {
            var inetAddress: InetAddress? = null
            try {
                val future: Future<InetAddress?>? =
                    Executors.newSingleThreadExecutor().submit(object : Callable<InetAddress?> {
                        override fun call(): InetAddress? {
                            return try {
                                InetAddress.getByName("google.com")
                            } catch (e: UnknownHostException) {
                                null
                            }
                        }
                    })
                inetAddress = future?.get(timeOut.toLong(), TimeUnit.MILLISECONDS)
                future?.cancel(true)
            } catch (e: InterruptedException) {
            } catch (e: ExecutionException) {
            } catch (e: TimeoutException) {
            }
            return inetAddress != null
        }
    }
}