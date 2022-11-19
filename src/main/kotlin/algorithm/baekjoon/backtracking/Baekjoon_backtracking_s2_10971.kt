package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val matrix = ArrayList<ArrayList<Int>>()
    for ( row in 0 until N ) {
        val cols = readln().split(" ").map{ it.toInt() } as ArrayList<Int>
        matrix.add(cols)
    }
    print( Q10971(N, matrix).solution() )
}
class Q10971( val N : Int, private val costMatrix : List<List<Int>> ) {
    fun solution() : Int {
        val minCostMatrix = Array(N) { Array(N) { Int.MAX_VALUE } }
        val indexs = Array(N) { -1 }
        val visits = Array(N) { false }
        fun dfs( depth : Int) {
            if ( depth == N ) {
                //println(indexs.joinToString())
                var cost = 0
                val startIndex = indexs[0]
                val endIndex = indexs[N-1]
                for ( i in 0 .. N - 2 )
                    cost += costMatrix[indexs[i]][indexs[i+1]]
                //println( "${minCostMatrix[startIndex][endIndex]} ::: ${cost}" )
                if ( minCostMatrix[startIndex][endIndex] > cost )
                    minCostMatrix[startIndex][endIndex] = cost
                //println( "${minCostMatrix[startIndex][endIndex]} ::: ${cost}" )

                return
            }

            for ( nextIndex in 0 until N ) {
                if ( visits[nextIndex] )
                    continue
                if ( depth > 0
                    && costMatrix[indexs[depth-1]][nextIndex] == 0)
                    continue

                visits[nextIndex] = true
                indexs[depth] = nextIndex
                dfs(depth + 1)
                visits[nextIndex] = false
                indexs[depth] = -1
            }
        }
        dfs(0)
        var minCost = Int.MAX_VALUE
//        for ( cols in minCostMatrix ) {
//            println( cols.joinToString() )
//        }

        for ( row in 0 until N-1)
            for ( col in 0 until N-1)
                if ( row == col )
                    continue
                else {
                    val cost = minCostMatrix[row][col]
                    if ( minCost > cost )
                        minCost = cost
                }
        for ( cols in minCostMatrix ) {
            println(cols.joinToString())
        }
        return minCost
    }
}