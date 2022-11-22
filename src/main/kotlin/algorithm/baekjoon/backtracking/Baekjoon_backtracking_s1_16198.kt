package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val WList = readln().split(" ").map { it.toInt() } as ArrayList<Int>

    print( Q16198( N, WList ).solution().toString() )
}
class Q16198 ( val N:Int, val WList: MutableList<Int> ) {
    fun solution() : Long {
        var maxResult = Long.MIN_VALUE


        val visits = Array(N) { false }
        val indexs = Array(N - 2 ) { -1 }

        fun dfs( depth : Int ) {
            if ( depth == N - 2 ) {
                var result :Long = 0

                val selects = Array(N) { false }
                //println(indexs.joinToString())
                for ( i in 0 .. N - 3 ){
                    val index = indexs[i]
                    selects[index] = true
                    var diff = 1
                    var left = -1
                    var right = -1

                    while ( 1 == 1 ) {

                        if ( left == -1 && index - diff > -1
                            && !selects[index-diff] ) {
                            left = index - diff
                        }
                        if ( right == -1 && index + diff < N
                            && !selects[index+diff] ) {
                            right = index + diff
                        }
                        //println("result : $result, index : $index, diff : $diff, left : $left, right : $right")

                        if ( left != -1 && right != -1 )
                            break
                        diff++
                    } // while
                    result += WList[left] * WList[right]

                } // for
                maxResult = maxOf(result, maxResult)
                return
            }
            for ( next in 1 ..N - 2 ) {
                if ( visits[next] )
                    continue
                visits[next] = true
                indexs[depth] = next
                dfs(depth + 1 )
                visits[next] = false
            }
        }
        dfs(0)


        return maxResult

    }
}