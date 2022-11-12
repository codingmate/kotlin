package algorithm.baekjoon

import algorithm.common.removeLastNewLine
fun main() {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    print(Q15651(N, M).solution())
}

class Q15651( private val N:Int, private val M:Int ) {
    fun solution () : String{
        val result = StringBuilder()
        val numList = ArrayList<Int>()

        fun backtracking(start:Int) {
            numList.add(start)

            for ( end in 1..N ) {
                if ( numList.size < M ) {
                    backtracking(end)
                }
            } // for : end

            if ( numList.size == M )
                result.append( numList.joinToString(separator = " ", postfix = "\n") )

            numList.removeLast()
        } // fun : backtracking

        for ( num in 1..N )
            backtracking(num)

        return result.toString().removeLastNewLine()
    }
}