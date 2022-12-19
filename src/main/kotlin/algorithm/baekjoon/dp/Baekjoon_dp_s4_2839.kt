package algorithm.baekjoon.dp

fun main() {
	val N = readln().toInt()
	print( Q2839().solution( N ) )
}
class Q2839 {

	fun solution(N: Int): Int {

		val seq = IntArray(5001) { -1 }
		seq[3] = 1
		seq[5] = 1
		for ( nums in 6 .. N step 1)
			if ( seq[nums-3] > -1 )
				seq[nums] = seq[nums-3] + 1

		for ( nums in 6 .. N step 1)
			if ( seq[nums-5] > -1 )
				seq[nums] = seq[nums-5] + 1


		return seq[N]
	}
}