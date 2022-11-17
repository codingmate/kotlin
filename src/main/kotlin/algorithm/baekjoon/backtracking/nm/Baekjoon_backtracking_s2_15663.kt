package algorithm.baekjoon.backtracking.nm

import algorithm.common.removeLastNewLine

fun main () {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    val numsASC = readln().split(" ").map{ it.toInt() }.sorted()

    print( Q15663(N, M, numsASC).solution().removeLastNewLine() )
}


class Q15663(private val N : Int, private val M : Int, private val nums : List<Int>) {
    fun solution(): String {

        val lineNumsIdx = Array(M) { -1 }

        val result = StringBuilder()


        fun  dfs(depth: Int) {
            if ( depth == M ) {
                lineNumsIdx.forEach { result.append("${nums[it]} ") }
                result.append("\n")
                return
            }

            var lastNum = -1
            for (nextIdx in 0 until N) {
                val nextNum = nums[nextIdx]
                when {
                    lastNum == nextNum -> continue
                    lineNumsIdx.contains(nextIdx) -> continue
                }

                lineNumsIdx[depth] = nextIdx
                lastNum = nums[nextIdx]
                dfs(depth + 1)
                lineNumsIdx[depth] = -1
            }

        }
        dfs(0)

        return result.toString()
    }
}