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
class Q10971( val N : Int, val matrix : List<List<Int>> ) {
    fun solution() : Int {
        var minCost = Int.MAX_VALUE
        val indexs = Array(N) { -1 }
        val visits = Array(N) { false }
        fun dfs( depth : Int) {
            if ( depth == N ) {
                var cost = 0
                for ( i in 0 .. N - 2 )
                    cost += matrix[indexs[i]][indexs[i+1]]
                cost += matrix[indexs[N-1]][indexs[0]]
                if ( minCost > cost )
                    minCost = cost
                return
            }

            for ( nextIndex in 0 until N ) {
                if ( visits[nextIndex])
                    continue

                visits[nextIndex] = true
                indexs[depth] = nextIndex
                dfs(depth + 1)
                visits[nextIndex] = false
                indexs[depth] = -1
            }
        }
        dfs(0)
        return minCost
    }
}