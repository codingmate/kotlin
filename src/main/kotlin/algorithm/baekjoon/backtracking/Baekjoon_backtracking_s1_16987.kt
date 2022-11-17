package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val eggList = ArrayList<egg>()
    for ( i in 0 until N ) {
        val dw = readln().split(" ")
        val d = dw[0].toInt()
        val w = dw[1].toInt()

        eggList.add(egg(d, w))
    }

    print(Q16987(N, eggList).solution())
}

class Q16987 ( val N : Int, val eggList : List<egg> ) {
    fun solution() : Int {

        val idxs = Array(N) { -1 }
        var maxCount = 0
        fun dfs( depth : Int ) {
            if ( depth == N ) {
                val copyEggList = ArrayList<egg>()
                for ( egg in eggList )
                    copyEggList.add(egg(egg.d, egg.w))

                for ( i in 0 until N ) {
                    val holdEgg = copyEggList[i]
                    if ( holdEgg.isBroken() )
                        continue
                    //println("$i, ${idxs[i]}")

                    val targetEgg = copyEggList[idxs[i]]

                    if ( targetEgg.isBroken() ) {
                        break
                    }
                    //print("${holdEgg.d} ${holdEgg.w} /// ")
                    //println("${targetEgg.d} ${targetEgg.w}")
                    holdEgg.d -= targetEgg.w
                    targetEgg.d -= holdEgg.w
                    //print("${holdEgg.d} ${holdEgg.w} /// ")
                    //println("${targetEgg.d} ${targetEgg.w}")
                } // for : i
                var count = 0

                for ( egg in copyEggList ) {
                    //print( egg.d.toString() + " ")
                    if( egg.isBroken() )
                        count++
                }
                //println()

                //val temp = StringBuilder()
                //print(idxs.joinToString(separator = " "))
                //println(if ( isCounting ) " !!!!" else "")
                maxCount = if ( count > maxCount ) count else maxCount
                return
            }
            for ( nextIdx in 0 until N ) {
                if ( depth == nextIdx )
                    continue
                idxs[depth] = nextIdx
                dfs(depth + 1 )
            } // for : nextIdx
        } // fun dfs()
        dfs(0)
        return maxCount
    } // fun solution()
}


data class egg( var d : Int, var w : Int ) {
    fun isBroken() : Boolean {
        return this.d < 1
    }
}