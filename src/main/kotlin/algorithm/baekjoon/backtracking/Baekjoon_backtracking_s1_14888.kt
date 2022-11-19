package algorithm.baekjoon.backtracking


fun main() = with( System.`in`.bufferedReader() ){
    val N = readln().toInt()
    val numList = readln().split(" ").map{ it.toInt() } as ArrayList<Int>
    val operCountList = readln().split(" ").map { it.toInt() } as ArrayList<Int>

    print( Q14888( N, numList, operCountList ).solution() )
}

class Q14888( val N : Int, val numList : List<Int>, val operCountList : List<Int>) {
    fun solution() : String {
        val operIndexCounts = Array(4) { 0 }
        val operIndexs = Array(N-1 ) { -1 }

        var maxResult = Int.MIN_VALUE
        var minResult = Int.MAX_VALUE

        fun dfs( depth : Int )  {
            if ( depth == N - 1 ) {
                var result = 0

                for ( index in 0 until N ) {
                    val num = numList[index]
                    if ( index == 0 )
                        result = num
                    else
                        result = when {
                            operIndexs[index-1] == 0 -> result + num
                            operIndexs[index-1] == 1 -> result - num
                            operIndexs[index-1] == 2 -> result * num
                            operIndexs[index-1] == 3 -> result / num
                            else -> -1
                        }
                }
                maxResult = if ( result > maxResult ) result else maxResult
                minResult = if ( result < minResult ) result else minResult
                return
            }

            for ( nextOperIndex in 0 .. 3) {
                if ( operIndexCounts[nextOperIndex] >= operCountList[nextOperIndex] )
                    continue

                operIndexCounts[nextOperIndex]++
                operIndexs[depth] = nextOperIndex

                dfs(depth + 1)

                operIndexCounts[nextOperIndex]--
                operIndexs[depth] = -1
            } // for : nextOperIndex
        } // fun dfs()
        dfs(0)

        return "${maxResult}\n${minResult}"
    }
}