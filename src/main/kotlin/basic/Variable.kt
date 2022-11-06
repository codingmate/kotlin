package basic

class Variable {
}

fun main() {
    /* Constant : final variable */
    val a: Int = 12
    val b = 23
    val c : Int
    c = 34
    // c = 47 => error

    val x = if ( a > b ) a else b
    val y = if ( a.compareTo(b) >= 0 ) a else b

    println("1. a : ${a}")
    println("2. b : ${b}")
    println("3. c : ${c}\n")
    println("4. x : ${x}")
    println("5. y : ${y}")


    /* variables */
    var z = 1
    z = 4 - 7; // reassigned
    // z = "1234" => error : type mismatch
    // var lambda = 1

    println("6. z : ${z}")
}
