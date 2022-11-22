package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val matrix = Array(N){ IntArray(N) }
    for ( row in 0 until N )
        matrix[row] = readln().split(" ").map{ it.toInt() }.toIntArray()
    print( Q10971(N, matrix).solution() )
}
class Q10971( val N : Int, private val costMatrix : Array<IntArray> ) {
    fun solution() : Int {

        var minResult = Int.MAX_VALUE
        val indexs = Array(N) { -1 }
        val visits = Array(N) { false }
        fun dfs( depth: Int ) {
            if ( depth == N ) {
                var result = 0
                if ( costMatrix[indexs[N-1]][indexs[0]] == 0 )
                    return
                for ( i in 0 until N - 1 )
                    result += costMatrix[indexs[i]][indexs[i+1]]
                result += costMatrix[indexs[N-1]][indexs[0]]
                minResult = minOf(minResult, result)
                return
            }
            for ( next in 0 until N ) {
                if ( visits[next] )
                    continue
                if ( depth > 0
                    && costMatrix[indexs[depth-1]][next] == 0)
                    continue

                visits[next] = true
                indexs[depth] = next
                dfs(depth + 1)
                visits[next] = false
            }
        }
        dfs(0)

        return minResult
    } // solution
}