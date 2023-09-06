package Adapter

import java.lang.UnsupportedOperationException
import java.util.Enumeration


class EnumerationIterator(
    private val enumeration: Enumeration<Int>
) : MutableIterator<Int> {
    override fun hasNext(): Boolean {
        return enumeration.hasMoreElements()
    }

    override fun next(): Int {
        return enumeration.nextElement()
    }

    override fun remove() {
        throw UnsupportedOperationException()
    }

}

class IteratorEnumeration(
    val iterator: Iterator<String>
) : Enumeration<String> {
    override fun hasMoreElements(): Boolean {
        return iterator.hasNext()
    }

    override fun nextElement(): String {
        return iterator.next()
    }

}


