package basic

class Function {
    fun del(a:Int, b:Int) : Int {
        return a - b
    }

    fun mul(a:Int, b:Int) : Int = a * b
}

fun main() {
    val func = Function()

    val del = func.del(7, 4)
    val mul = func.mul( a = 15, b = 3)

    val result = StringBuilder()

    result.append("1. del : ${del}\n")
    result.append("2. mul : ${mul}")

    print(result)
    /*
        1. del : 32. mul : 3
     */
}