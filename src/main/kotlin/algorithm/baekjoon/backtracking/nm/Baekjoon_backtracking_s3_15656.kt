package algorithm.baekjoon.backtracking.nm

import algorithm.common.joinToStringBuilder
import algorithm.common.removeLastNewLine

fun main () {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    val numsASC = readln().split(" ").map{ it.toInt() }.sorted()


    print( Q15656(N, M, numsASC).solution().removeLastNewLine() )
}


class Q15656(private val N : Int, private val M : Int, private val nums : List<Int>) {
    fun solution(): String {

        val lineNums = Array(M) { 0 }

        val result = StringBuilder()
        fun dfs(depth: Int) {
            if (depth == M) {
                result.append(lineNums.joinToStringBuilder(separator = " ", postfix = "\n"))
                return
            }
            for (next in 0 until N) {
                lineNums[depth] = nums[next]
                dfs(depth + 1)
            }

        }
        dfs(0)

        return result.toString()
    }
}