package algorithm.baekjoon.dp
fun main() {
	print(Q11053().solution(readln().toInt()))
}
class Q11053 {
	fun solution(N: Int): Int {
		val nums = readln().split(" ").map{ it.toInt() }
		val counts = IntArray(N)
		counts[0] = 1

		var maxCount = Int.MIN_VALUE

		for ( i in 0 until N ) {
			counts[i] = 1
			var leftMaxCount = 0
			for ( j in i-1 downTo 0)
				if ( nums[i] > nums[j] )
					leftMaxCount = maxOf(leftMaxCount, counts[j])
			counts[i] = leftMaxCount + 1
			maxCount = maxOf(maxCount, counts[i])
		}

		return maxCount
	}
}