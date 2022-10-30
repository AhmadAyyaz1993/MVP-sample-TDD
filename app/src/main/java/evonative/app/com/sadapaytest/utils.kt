package evonative.app.com.sadapaytest

import java.net.InetAddress
import java.net.UnknownHostException
import java.text.DecimalFormat
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

        fun prettyCount(number: Number): String? {
            val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
            val numValue = number.toLong()
            val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
            val base = value / 3
            return if (value >= 3 && base < suffix.size) {
                DecimalFormat("#0.0").format(
                    numValue / Math.pow(
                        10.0,
                        (base * 3).toDouble()
                    )
                ) + suffix[base]
            } else {
                DecimalFormat("#,##0").format(numValue)
            }
        }
    }
}