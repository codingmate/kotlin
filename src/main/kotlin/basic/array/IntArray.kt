package basic.array

fun main () {
    println(Array().intArrayInitValues().joinToString())
    // 0, 0, 0, 0, 0
    println(Array().intArrayDefaultValues().joinToString())
    // -1, -1, -1, -1, -1
}
class Array {
    fun intArrayInitValues(): IntArray {
        return IntArray(5)
    }

    fun intArrayDefaultValues(): IntArray {
        return IntArray(5) { -1 }
    }
}