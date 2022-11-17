package algorithm.baekjoon.backtracking.nm

import algorithm.common.removeLastNewLine

fun main() {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    val numsASC = readln().split(" ").map{ it.toInt() }.sorted()
    print(Q15664(N, M, numsASC).solution().removeLastNewLine())


}


class Q15664(val N:Int, val M:Int, val nums : List<Int>) {
    fun solution() : String {
        val result = StringBuilder()
        val idxs = Array(M){ -1 }

        fun dfs ( depth : Int ) {
            if ( depth == M ) {
                idxs.forEach{ result.append("${nums[it]} ") }
                result.append("\n")
                return
            }

            var lastNum = -1
            for ( nextIdx in 0 until N ) {

                val nextNum = nums[nextIdx]
                when {
                    depth > 0 && nums[idxs[depth-1]] > nextNum -> continue
                    lastNum == nextNum -> continue
                    idxs.contains(nextIdx) -> continue
                }
                idxs[depth] = nextIdx
                lastNum = nextNum
                dfs(depth + 1)
                idxs[depth] = -1
            } // for : nextIdx

        }

        dfs(0)


        return result.toString()
    }
}