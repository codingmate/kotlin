package algorithm.baekjoon.dp

fun main() {
	print( Q2156().solution(readln().toInt()) )
}

class Q2156 {

	fun solution(n: Int): Int {

		val dp = IntArray( n + 1 )
		val nums = IntArray( n + 1 )

		nums[1] = readln().toInt()
		if ( n == 1 ) return nums[1]
		dp[1] = nums[1]
		nums[2] = readln().toInt()
		dp[2] = nums[2] + dp[1]
		if ( n == 2 ) return dp[2]
		nums[3] = readln().toInt()
		dp[3] = maxOf( nums[3] + maxOf(nums[1], nums[2]), dp[2] )
		if ( n == 3 ) return dp[3]

		for ( i in 4 .. n ) {
			nums[i] = readln().toInt()
			dp[i] = maxOf( nums[i] + maxOf(dp[i - 3] + nums[i - 1], dp[i - 2]), dp[i-1] )
		}
		var max = Int.MIN_VALUE
		for ( i in 1 .. n )
			max = maxOf(max, dp[i])


		return max
	}
}