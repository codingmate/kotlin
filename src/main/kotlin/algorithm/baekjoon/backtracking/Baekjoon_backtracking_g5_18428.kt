package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()
    val matrix =  ArrayList<ArrayList<String>>()
    for ( row in 0 until N )
        matrix.add( readln().split(" ") as ArrayList<String> )
    print( Q18428( N, matrix ).solution() )
}


class Q18428( val N : Int, val matrix : List<List<String>> ) {
    fun solution() : String {
        val O_COUNT = 3

        val copyMatrix = ArrayList< ArrayList<String> >()
        for ( row in 0 until N ) {
            val cols = ArrayList<String>()
            copyMatrix.add(cols)
            for ( col in 0 until N )
                cols.add( matrix[row][col] )
        }

        val XList = ArrayList<RC>()
        val SList = ArrayList<RC>()
        val TList = ArrayList<RC>()
        for ( row in 0 until N )
            for ( col in 0 until N )
                when {
                    matrix[row][col] == "X" -> XList.add(RC(row, col, "X"))
                    matrix[row][col] == "S" -> SList.add(RC(row, col, "S"))
                    matrix[row][col] == "T" -> TList.add(RC(row, col, "T"))
                }
        val O_indexs = Array(O_COUNT) { -1 }
        var result = false
        fun dfs( depth : Int ) {
            if ( result )
                return
            if ( depth == O_COUNT ) {
                for ( index in O_indexs ) {
                    val R = XList[index].R
                    val C = XList[index].C
                    copyMatrix[R][C] = "O"
                }

                var isDetected = false
                for ( T in TList ) {
                    if ( isDetected )
                        break
                    val R = T.R
                    val C = T.C
                    var needUp = R > 1
                    var needDown = R < N - 1
                    var needLeft = C > 1
                    var needRight = C < N - 1
                    for ( diff in 1 .. N - 2 ) {
                        if ( needUp && R - diff > -1 )
                            when ( copyMatrix[R - diff][C] ) {
                                "S" -> {
                                    isDetected = true
                                    break
                                }
                                "O" -> needUp = false
                            }
                        else if ( R - diff < 0 )
                            needUp = false

                        if ( needDown && R + diff < N )
                            when ( copyMatrix[R + diff][C] ) {
                                "S" -> {
                                    isDetected = true
                                    break
                                }
                                "O" -> needDown = false
                            }
                        else if ( R + diff > N - 1 )
                            needDown = false

                        if ( needLeft && C - diff > -1 )
                            when (copyMatrix[R][C - diff] ) {
                                "S" -> {
                                    isDetected = true
                                    break
                                }
                                "O" -> needLeft = false
                            }
                        else if ( C - diff < 0 )
                            needLeft = false

                        if ( needRight && C + diff < N )
                            when ( copyMatrix[R][C + diff] ) {
                                "S" -> {
                                    isDetected = true
                                    break
                                }
                                "O" -> needRight = false
                            }
                        else if ( C + diff > N - 1 )
                            needRight = false
                        //println("R = $R, C = $C, up : $needUp, down : $needDown, left : $needLeft, right : $needRight")
                        //for ( index in O_indexs ) {
                        //    val R = XList[index].R
                        //    val C = XList[index].C
                        //    println(" O : $R, $C ")
                        //}
                        //println("isDetected : $isDetected")
                    } // for : diff
                } // for : T
                if ( !isDetected )
                    result = true

                if ( result )
                    return

                //for ( cols in copyMatrix ) {
                //    for ( col in cols)
                //        print(col + " ")
                //    println()
                //}


                for ( index in O_indexs ) {
                    val R = XList[index].R
                    val C = XList[index].C
                    copyMatrix[R][C] = "X"
                }
                return
            }
            val startIndex = if ( depth > 0 ) O_indexs[depth - 1] + 1 else 0
            for ( next in startIndex until XList.size ) {
                O_indexs[depth] = next
                dfs(depth + 1)
            }
        }
        dfs(0)



        return if ( result ) "YES" else "NO"
    }

    data class RC( val R:Int, val C:Int, val value: String )
}