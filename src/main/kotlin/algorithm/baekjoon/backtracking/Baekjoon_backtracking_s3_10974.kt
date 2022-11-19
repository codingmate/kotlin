package algorithm.baekjoon.backtracking

fun main() {
    val N = readln().toInt()

    print(Q10974(N).solution())
}

class Q10974(val N:Int) {
    fun solution() : String {
        val result = StringBuilder()
        val visits = Array(N) { false }
        val nums = Array(N) { -1 }
        fun dfs( depth: Int ) {
            if ( depth == N ) {
                result.append(nums.joinToString(separator = " ", postfix = "\n"))
                return
            }
            for ( nextIndex in 0 until N ) {
                if ( visits[nextIndex] )
                    continue
                visits[nextIndex] = true
                nums[depth] = nextIndex + 1
                dfs(depth + 1)
                visits[nextIndex] = false
            }
        }
        dfs(0)
        return result.toString()
    }
}