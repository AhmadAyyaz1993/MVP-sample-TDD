package evonative.app.com.sadapaytest.utils

import java.io.InputStreamReader

class MockResponseFile(path: String) {
    val content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}