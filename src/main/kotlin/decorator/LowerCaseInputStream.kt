package decorator

import java.io.FilterInputStream
import java.io.InputStream

class LowerCaseInputStream(private val inputStream: InputStream) : FilterInputStream(inputStream) {

    override fun read(): Int {

        val c = inputStream.read()
        return super.read()
    }
}