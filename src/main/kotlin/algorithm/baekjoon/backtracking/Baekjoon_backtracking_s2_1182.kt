package algorithm.baekjoon.backtracking

fun main() {
    val NS = readln().split(" ").map{it.toInt()}
    val N = NS[0]
    val S = NS[1]

    val numList = readln().split(" ").map{it.toInt()} as ArrayList<Int>
    print ( Q1182(N, S, numList).solution() )
}

class Q1182( val N: Int, val S: Int, val numList: List<Int> ) {
    fun solution() : String {

        val visits = Array(N) { false }
        val indexs = Array(N) { -1 }
        var sum = 0

        var count = 0
        fun dfs( depth: Int ) {
            if ( depth == N )
                return

            for ( nextIndex in 0 until N ) {
                if ( visits[nextIndex] )
                    continue
                if ( depth > 0 )
                    if ( indexs[depth - 1] > nextIndex )
                        continue

                visits[nextIndex] = true
                val nextNum = numList[nextIndex]
                indexs[depth] = nextIndex

                sum += nextNum
                if ( sum == S )
                    count++

                dfs(depth + 1)

                visits[nextIndex] = false
                sum -= nextNum
                indexs[depth] = -1
            } // for : nextIndex
        } // fun dfs()
        dfs(0)

        return count.toString()
    } // fun solution()

}