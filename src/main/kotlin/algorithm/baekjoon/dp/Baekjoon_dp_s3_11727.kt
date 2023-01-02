import java.io.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine()!!.toInt()
	print(Q11727().solution(N))
}

class Q11727 {
    fun solution(N: Int): Long {
        val dp = LongArray(N+1)
        
        if ( N == 1 ) return 1
        dp[1] = 1
        dp[2] = 3

        for ( n in 3 .. N )
            dp[n] = ( dp[n-1] % 10007 + 2 * dp[n-2] % 10007 ) % 10007
        //println(dp.joinToString())
        return dp[N]
    }
}
