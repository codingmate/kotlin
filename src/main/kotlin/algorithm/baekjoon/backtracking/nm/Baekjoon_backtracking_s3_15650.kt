package algorithm.baekjoon.backtracking.nm

fun main() {
    val NM = readln().split(" ").map { it.toInt() }

    val N = NM[0]
    val M = NM[1]

    print( Q15650(N, M).solution() )
}

class Q15650 ( private val N:Int, private val M:Int ){
    fun solution() : String {
        val visits = BooleanArray(N + 1 ){ false }
        val result = StringBuilder()
        val numList = ArrayList<Int>()

        fun backtracking(start:Int) {
            visits[start] = true
            numList.add(start)

            for ( end in 1..N ) {
                if ( !visits[end]
                    && numList.size < M
                    && start < end ) {
                    backtracking(end)
                }
            } // for : end

            if ( numList.size == M )
                result.append( numList.joinToString(separator = " ", postfix = "\n") )

            numList.removeLast()
            visits[start] = false
        } // fun : backtracking
        for ( num in 1..N)
            backtracking(num)

        return ( if ( result.length > 1 ) result.deleteCharAt(result.length - 1) else result ).toString()
    } // fun : solution
}