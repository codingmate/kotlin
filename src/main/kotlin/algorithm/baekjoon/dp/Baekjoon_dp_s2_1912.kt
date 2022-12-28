package algorithm.baekjoon.dp

fun main() {
	print( Q1912().solution(readln().toInt()) )
}
class Q1912 {

	fun solution(n: Int): Int {

		val nums = readln().split(" ").map{ it.toInt() }

		val dp = IntArray(n)
		dp[0] = nums[0]
		var max = dp[0]
		for ( i in 1 until n ) {
			dp[i] = nums[i] + if (dp[i - 1] > 0) dp[i - 1] else 0
			max = maxOf(max, dp[i])
		}
		return max
	}
}