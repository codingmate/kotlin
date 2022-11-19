package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val matrix = Array(N){ IntArray(N) }
    for ( row in 0 until N ) {
        val cols = readln().split(" ").map{ it.toInt() }.toIntArray()
        matrix[row] = cols
    }
    print( Q10971(N, matrix).solution() )
}
class Q10971( val N : Int, private val costMatrix : Array<IntArray> ) {
    fun solution() : Int {
        val goMinCostMatrix = Array(N) { Array(N) { Int.MAX_VALUE } }
        val backMinCostMatrix = Array(N){ Array(N) { Int.MAX_VALUE } }
        for ( row in 0 until N )
            for ( col in 0 until N )
                backMinCostMatrix[row][col] = costMatrix[row][col]


        val visits = BooleanArray(N) { false }
        val indexs = IntArray(N) { -1 }

        var backMinCost = 0
        fun dfs( depth: Int ) {
            if ( depth == N ) {
                var cost = 0
                for ( i in 0 .. N-2 )
                    cost += costMatrix[indexs[i]][indexs[i+1]]
                goMinCostMatrix[indexs[0]][indexs[N-1]] = minOf(goMinCostMatrix[indexs[0]][indexs[N-1]], cost)
                return
            }
            for ( nextIndex in 0 until N ) {
                if ( visits[nextIndex] )
                    continue
                if ( depth > 0 ) {
                    when {
                        costMatrix[indexs[depth - 1 ]][nextIndex] == 0 -> continue
                    }
                }
                visits[nextIndex] = true
                indexs[depth] = nextIndex


                val nextMoveCost = if ( depth > 0 ) costMatrix[indexs[depth-1]][indexs[depth]] else 0
                backMinCost += nextMoveCost
                if ( depth > 0 ) {
                    val prevMin = backMinCostMatrix[indexs[0]][indexs[depth]]
                    if ( prevMin > backMinCost ) {
                        backMinCostMatrix[indexs[0]][indexs[depth]] = backMinCost
                    }
                }
                dfs(depth + 1)

                visits[nextIndex] = false
                indexs[depth] = -1
                backMinCost -= nextMoveCost
            }
        } // dfs
        dfs(0)

//        println("go")
//        for ( cols in goMinCostMatrix )
//            println(cols.joinToString())
//        println("back")
//        for ( cols in backMinCostMatrix )
//            println(cols.joinToString())

        var minCost = Int.MAX_VALUE
        for ( row in 0 until N ) {
            for ( col in 0 until N ) {
                if ( row == col )
                    continue
                else {
                    val cost = goMinCostMatrix[row][col] + backMinCostMatrix[col][row]
                    //println( "${goMinCostMatrix[row][col]} :: ${backMinCostMatrix[col][row]} = ${cost}" )
                    if ( minCost > cost )
                        minCost = cost
                }
            }
        }
        return minCost
    } // solution
}