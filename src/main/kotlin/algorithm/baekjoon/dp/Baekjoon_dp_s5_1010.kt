package algorithm.baekjoon.dp

fun main() {
	print ( Q1010().solution(readln().toInt()) )
}

class Q1010 {
	fun solution(T: Int): String {

		val dp = Array(30) { IntArray(30) }

		for ( num in 1 until 30 )
			dp[1][num] = num

		for ( n in 2 until 30 )
			for ( m in n until 30 )
				dp[n][m] = dp[n][m-1] + dp[n-1][m-1]
		val result = StringBuilder()
		for ( rowCount in 1 .. T ) {
			val NM = readln().split(" ").map { it.toInt() }
			val N = NM[0]
			val M = NM[1]
			result.append("${dp[N][M]}\n")
		}
		//for ( obj in dp )
		//	println( obj.joinToString() )
		return result.toString()
	}
}