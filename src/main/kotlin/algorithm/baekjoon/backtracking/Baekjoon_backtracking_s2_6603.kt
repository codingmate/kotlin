package algorithm.baekjoon.backtracking

import java.util.LinkedList

internal fun main() {

    val lineList = LinkedList<String>()
    while ( true ) {
        val line = readln()
        if ( line.length > 1 )
            lineList.add(line)
        else
            break
    }
    println( Q6603(lineList).solution() )

}

const val LOTTO_NUM_COUNT = 6

internal class Q6603 ( val lines : List<String> ) {

    fun solution() : String {

        val result = StringBuilder()
        for ( line in lines ) {

            val nums = line.split(" ").map{ it.toInt() }.toMutableList()
            val k = nums[0]
            nums.removeAt(0)

            val idxs = Array(LOTTO_NUM_COUNT){ -1 }


            fun dfs( depth : Int ) {
                if ( depth == LOTTO_NUM_COUNT ) {
                    idxs.forEach { idx -> result.append(nums[idx].toString() + " ") }
                    result.append("\n")
                    return
                }

                for ( nextIdx in 0 until k ) {
                    if ( depth > 0 ) {
                        val curNum = nums[ idxs[depth - 1] ]
                        val nextNum = nums [ nextIdx ]
                        if ( curNum >= nextNum )
                            continue
                    }
                    idxs[depth] = nextIdx
                    dfs(depth + 1)
                }
            }

            dfs(0)
            result.append("\n")
        } // for : lines

        if ( result.length > 1 )
            result.deleteCharAt(result.lastIndexOf("\n"))

        return result.toString()
    } // fun : solution
}