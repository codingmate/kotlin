package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val numList = readln().split(" ").map{ it.toInt() }

    print( Q10819(N, numList).solution() )


}
class Q10819( val N: Int, val numList: List<Int> ) {

    fun solution() : Int {
        val visits = Array(N) { false }
        val indexs = Array(N) { -1 }

        var maxResult = Int.MIN_VALUE
        fun backtracking( depth :Int ) {
            if ( depth == N ) {
                var result = 0
                for ( index in 0 .. N - 2 ) {
                    val num = numList[indexs[index]]
                    val nextNum = numList[indexs[index + 1]]
                    result += if ( num > nextNum ) num - nextNum
                            else nextNum - num
                }
                if ( result > maxResult )
                    maxResult = result
                return
            }

            for ( nextIndex in 0 until N ) {
                if ( visits[nextIndex] )
                    continue

                visits[nextIndex] = true
                indexs[depth] = nextIndex
                backtracking(depth + 1)
                visits[nextIndex] = false
            }
        }

        backtracking(0)
        return maxResult
    } // fun solution()
}