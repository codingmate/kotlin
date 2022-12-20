package algorithm.baekjoon.dp

fun main() {
	print( Q9095().solution(readln().toInt()) )
}


class Q9095 {
	fun solution(T: Int): String {
		val dp = IntArray(11)
		dp[0] = 1
		dp[1] = 1
		dp[2] = 2

		var top = 2
		val result = StringBuilder()
		for ( row in 1 .. T ) {
			val n = readln().toInt()
			if ( n > top ) {
				while ( top < n ) {
					top++
					dp[top] = dp[top - 1] + dp[top - 2] + dp[top - 3]
				}
			}
			result.append("${dp[n]}\n")
		}
		return result.toString()
	}

}