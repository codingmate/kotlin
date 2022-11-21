package algorithm.baekjoon.backtracking

fun main() {
    print(Q15658(readln().toInt(), readln().split(" ").map{ it.toInt()}, readln().split(" ").map{it.toInt()}).solution())
}

class Q15658(val N:Int, val numList: List<Int>, val maxOperCountList: List<Int> ) {
    fun solution(): String {
        var minResult = Int.MAX_VALUE
        var maxResult = Int.MIN_VALUE

        val operCountList = Array(4) { 0 }
        val opers = Array(N - 1 ) { "" }

        fun backtracking(depth:Int) {
            if ( depth == numList.size - 1 ) {
                //println(opers.joinToString())
                var result = numList[0]
                opers.forEachIndexed {
                    index, it -> when ( it )  {
                        "+" -> result += numList[index + 1]
                        "-" -> result -= numList[index + 1]
                        "*" -> result *= numList[index + 1]
                        "/" -> result /= numList[index + 1] }
                }

                if ( result > maxResult )
                    maxResult = result
                if ( result < minResult )
                    minResult = result
                return
            }

            for ( nextIndex in 0 .. 3 ) {
                if ( operCountList[nextIndex] == maxOperCountList[nextIndex] )
                    continue
                operCountList[nextIndex]++
                opers[depth] = when ( nextIndex ) {
                    0 -> "+"
                    1 -> "-"
                    2 -> "*"
                    3 -> "/"
                    else -> ""
                }
                backtracking(depth + 1)
                operCountList[nextIndex]--
            }

        }
        backtracking(0)
        return maxResult.toString() + "\n" + minResult.toString()
    }
}