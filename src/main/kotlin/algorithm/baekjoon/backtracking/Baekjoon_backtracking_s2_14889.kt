package algorithm.baekjoon.backtracking



fun main() {
    val N = readln().toInt()
    val matrix = ArrayList<ArrayList<Int>>()

    for ( row in 0 until N) {
        val lineNumList = readln().split(" ").map{ it.toInt() } as ArrayList<Int>
        matrix.add(lineNumList)
    }
    print( Q14889(N, matrix).solution() )
}

internal class Q14889 ( val N : Int, private val matrix : List<List<Int>>){

    fun solution() : String {
        class Team( private val teamIndexList : List<Int> ) {

            fun getTotalStatVal() : Int {
                var total = 0
                for ( row in teamIndexList )
                    for ( col in teamIndexList )
                        total += matrix[row][col]
                return total
            }

            fun getOpposingTeam() : Team {
                val opposingIndexList = ArrayList<Int>()
                for ( index in 0 until N )
                    if ( teamIndexList.contains(index) ) continue
                    else opposingIndexList.add(index)

                return Team(opposingIndexList)
            }
        }

        val visits = Array(N) { false }
        val indexs = Array(N/2) { -1 }
        var minDiff = Int.MAX_VALUE

        fun dfs ( depth : Int ) {
            if ( depth == N/2 ) {
                val team = Team( indexs.toList() as ArrayList<Int> )
                val opposingTeam = team.getOpposingTeam()
                var diff = team.getTotalStatVal() - opposingTeam.getTotalStatVal()
                diff = if ( diff < 0 ) -1 * diff else diff
                minDiff = if ( minDiff > diff ) diff else minDiff
                return
            }

            for ( nextIdx in 0 until N ) {
                if ( visits[nextIdx] )
                    continue
                if ( depth > 0 ) {
                    when {
                        indexs[depth - 1] >= nextIdx -> continue
                    }
                }

                visits[nextIdx] = true
                indexs[depth] = nextIdx

                dfs( depth + 1 )
                visits[nextIdx] = false
                indexs[depth] = -1
            }
        }
        dfs(0)

        return minDiff.toString()
    }


}

