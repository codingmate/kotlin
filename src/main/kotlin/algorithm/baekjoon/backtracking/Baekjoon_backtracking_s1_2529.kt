package algorithm.baekjoon.backtracking

fun main() {
    print( Q2529( readln().toInt(), readln() ).solution() )
}

class Q2529 ( val k: Int, val line : String ) {
    fun solution() : String {
        var maxResult : Long = Long.MIN_VALUE
        var maxResultStr = ""

        var minResult : Long = Long.MAX_VALUE
        var minResultStr = ""

        val operList = line.split(" ") as ArrayList<String>
        val visits = Array(10 ) { false }
        val nums = Array(k + 1) { -1 }
        fun dfs(depth: Int) {
            if ( depth == k + 1 ) {
                val resultStr = nums.joinToString(separator = "")
                val result = resultStr.toLong()
                if ( result > maxResult ) {
                    maxResult = result
                    maxResultStr = resultStr
                }
                if ( result < minResult ) {
                    minResult = result
                    minResultStr = resultStr
                }
                return
            }

            var startNum = 0
            var endNum = 9
            if ( depth > 0 ) {
                when (operList[depth - 1]) {
                    ">" -> {
                        startNum = 0
                        endNum = nums[depth - 1] - 1
                    }
                    "<" -> {
                        startNum = nums[depth - 1] + 1
                        endNum = 9
                    }
                }
            }

            for ( nextNum in startNum .. endNum ) {
                if ( visits[nextNum] )
                    continue
                visits[nextNum] = true
                nums[depth] = nextNum
                dfs(depth + 1)

                visits[nextNum] = false
            }
        }
        dfs(0)
        return maxResultStr + "\n" + minResultStr
    }
}