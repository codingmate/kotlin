package algorithm.baekjoon.backtracking

fun main () {
    val NK = readln().split(" ")
    val N = NK[0].toInt()
    val K = NK[1].toInt()

    val numList = readln().split(" ").map{ it.toInt() }

    print(Q18429(N, K, numList).solution())

}
class Q18429 (private val N : Int, private val K : Int, private val numList : List<Int> ){

    fun solution() : String {
        var result = 0
        val numIdxs = Array(N) { -1 }

        var W = 500
        fun dfs( depth : Int ) {
            if ( depth == N && W + numList[numIdxs[depth-1]] >= 500 ) {
                result++
                return
            }
            W -= K
            for ( nextIdx in 0 until N ) {
                val nextW = numList[nextIdx]
                W += nextW
                if ( numIdxs.contains(nextIdx)
                    || W < 500 ) {
                    W -= nextW
                    continue
                }

                numIdxs[depth] = nextIdx
                dfs ( depth + 1)
                numIdxs[depth] = -1
                W -= nextW
            } // for : nextIdx
            W += K
        }
        dfs(0)
        return result.toString()
    }
}