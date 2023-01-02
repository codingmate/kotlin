import java.io.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine()!!.toInt()
	print(Q10844().solution(N))
}

class Q10844 {
    fun solution(N: Int): Long {
        val dp = IntArray(10) { 1 }
        dp[0] = 0
        val tempDp = IntArray(10)
        val billion = 1000000000
        for ( rowCount in 1 until N ) {
            for ( i in dp.indices )
                tempDp[i] = dp[i]
            
            dp[0] = tempDp[1]
            for ( i in 1 .. 8 )
                dp[i] = ( tempDp[i-1] % billion + tempDp[i+1] % billion ) % billion
            dp[9] = tempDp[8]
        }
        var total= 0L
        dp.forEach {
            value -> total += value
        }
        //println(dp.joinToString())
        
        return total % billion
    }
}
