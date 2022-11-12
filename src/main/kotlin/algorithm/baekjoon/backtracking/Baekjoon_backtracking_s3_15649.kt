package algorithm.baekjoon.backtracking

import java.util.ArrayList

fun main() {
    val NM = readln().split(" ").map { it.toInt() }

    val N = NM[0]
    val M = NM[1]

    print( Q15649(N, M).solution() )
}

class Q15649( private val N:Int, private val M:Int ) {

    fun solution() : String{
        val graph = HashMap<Int, ArrayList<Int>>()

        for ( start in 1..N ) {
            val childList = ArrayList<Int>()
            graph[start] = childList
            for ( end in 1..N )
                if ( start != end )
                    childList.add(end)
        }

        val result = StringBuilder()
        val printList = ArrayList<Int>()

        fun backtracking(start : Int ) {
            printList.add(start)

            for ( end in graph[start]!! )
                if ( printList.size < M
                    && printList.indexOf(end) == -1 )
                    backtracking(end)
            if ( printList.size == M ) {
                result.append(printList.joinToString(separator = " ", postfix = "\n" ) )
            }
            printList.removeLast()
        }

        for ( start in 1..N ) {
            backtracking(start)
        }

        return if ( result.length > 1 ) result.deleteCharAt(result.length - 1).toString()
             else ""

    }

}


