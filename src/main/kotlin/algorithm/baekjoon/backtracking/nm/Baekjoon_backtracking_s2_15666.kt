package algorithm.baekjoon.backtracking.nm

internal fun main() {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    val numListDistinctASC = readln().split(" ").map{ it.toInt() }.distinct().sorted()

    print( Q15666( N, M, numListDistinctASC ).solution() )
}


class Q15666( val N : Int, val M : Int, val numList : List<Int>) {
    internal fun solution() : String {
        val result = StringBuilder()

        val lineIdxs = Array(M){ -1 }

        fun dfs( depth : Int ) {

            if ( depth == M ) {
                lineIdxs.forEach{ result.append(numList[it].toString() + " ") }
                result.append("\n")
                //result.append( lineIdxs.joinToString(separator = " ", postfix = "\n") )
                return
            }

            var lastNum = -1
            for ( nextIdx in numList.indices) {
                val nextNum = numList[nextIdx]
                if ( lastNum == nextNum )
                    continue

                if ( depth > 0 ) {
                    when {
                        numList[lineIdxs[depth - 1]] > nextNum -> continue
                    }
                }

                lineIdxs[depth] = nextIdx
                dfs(depth + 1)
            }
        }

        dfs(0)

        return result.toString()
    }
}