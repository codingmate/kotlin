package basic.array

fun main () {
    println(Array().intArrayInitValues().joinToString())

    println(Array().intArrayDefaultValues().joinToString())
}
class Array {
    fun intArrayInitValues(): IntArray {
        return IntArray(5) // 0, 0, 0, 0, 0
    }

    fun intArrayDefaultValues(): IntArray {
        return IntArray(5) { -1 }
    }
}