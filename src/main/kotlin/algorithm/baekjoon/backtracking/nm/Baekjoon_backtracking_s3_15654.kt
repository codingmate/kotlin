package algorithm.baekjoon.backtracking.nm

import algorithm.common.removeLastNewLine

fun main() {
    val NM = readln().split(" ")
    val N = NM[0].toInt()
    val M = NM[1].toInt()

    val nums = readln().split(" ").map{ num -> num.toInt() }.toIntArray()

    print(Q15654(N, M, nums).solution())
}

class Q15654( private val N:Int, private val M:Int, private val nums:IntArray ) {
    fun solution () : String{
        val result = StringBuilder()
        val numList = ArrayList<Int>()
        val visitIndexs = Array(N){false}
        nums.sort()

        fun backtracking(numsStartIndex:Int) {
            numList.add(nums[numsStartIndex])
            visitIndexs[numsStartIndex] = true

            for ( numsEndIndex in 0 until N ) {
                if ( numList.size < M
                    && !visitIndexs[numsEndIndex]) {
                    backtracking(numsEndIndex)
                }
            } // for : end

            if ( numList.size == M )
                result.append( numList.joinToString(separator = " ", postfix = "\n") )

            visitIndexs[numsStartIndex] = false
            numList.removeLast()
        } // fun : backtracking

        for ( numsIndex in 0 until N )
            backtracking(numsIndex)

        return result.toString().removeLastNewLine()
    }
}