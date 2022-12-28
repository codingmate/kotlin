package algorithm.baekjoon.dp

fun main() {
	print( Q9461().solution(readln().toInt()) )
}
class Q9461 {
	fun solution(T: Int): String {
		val result = StringBuilder()

		val dp = LongArray(101)
		dp[1] = 1
		dp[2] = 1
		dp[3] = 1
		dp[4] = 2
		dp[5] = 2
		var top = 5
		for ( rowCount in 0 until T ) {
			val N = readln().toInt()
			if ( N > top ) {
				for ( i in top + 1 .. N )
					dp[i] = dp[i-1] + dp[i-5]
				top = N
			}
			result.append("${dp[N]}\n")
		}

		return result.toString()
	}
}