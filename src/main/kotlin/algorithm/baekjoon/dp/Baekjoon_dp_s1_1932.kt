package algorithm.baekjoon.dp

fun main() {
	print(Q1932().solution(readln().toInt()))
}

class Q1932 {
	fun solution(N: Int): Int {
		val dp = Array(N + 1){ IntArray(N + 2) }
		dp[1][1] = readln().toInt()
		var max = dp[1][1]
		for ( row in 2 .. N ) {
			val nums = readln().split(" ").map{ it.toInt() }
			for ( col in 1 .. row ) {
				dp[row][col] = nums[col-1] + maxOf(dp[row - 1][col], dp[row - 1][col - 1])
				max = maxOf(max, dp[row][col])
			} // for : col
		} // for : row
		return max
	}
}