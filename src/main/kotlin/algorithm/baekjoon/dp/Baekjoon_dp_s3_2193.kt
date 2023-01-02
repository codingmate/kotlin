import java.io.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine()!!.toInt()
	print(Q2193().solution(N))
}

class Q2193 {
    fun solution(N: Int): Long {
        val dp = LongArray(N+1)
        
        if ( N <= 2 ) return 1
        dp[1] = 1
        dp[2] = 1

        for ( i in 3 .. N ) {
            dp[i] = 1
            for ( j in 2 until i )
                dp[i] += dp[i-j]
        }
        //print(dp.joinToString())
        return dp[N]
    }
}
