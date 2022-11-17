package algorithm.baekjoon.backtracking.nm

internal fun main() {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()
    val numsDistinctASC = readln().split(" ").map{ it.toInt() }.distinct().sorted()


    print( Q15665(N, M, numsDistinctASC).solution() )
}

internal class Q15665( val N : Int, val M : Int, val nums : List<Int> ) {
    fun solution() : String {
        val result = StringBuilder()
        val dLength = nums.size

        val numList = Array(M){ -1 }

        fun dfs( depth : Int ) {
            if ( depth == M ) {
                result.append( numList.joinToString( separator = " ", postfix = "\n" ) )
                return
            } // if : depth

            for ( nextIdx in 0 until dLength) {
                val nextNum = nums[nextIdx]
                numList[depth] = nextNum
                dfs(depth + 1)
            } // for : idx
        }

        dfs(0)

        return result.toString()
    }

}