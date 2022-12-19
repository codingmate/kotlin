package algorithm.baekjoon.dp

fun main() {
	val N = readln().toInt()
	print( Q1463().solution( N ) )
}

class Q1463 {
	fun solution(N: Int): Int {
		val counts = IntArray(1000001) { -1 }
		counts[1] = 0
		counts[2] = 1
		counts[3] = 1

		for ( nums in 1 .. N - 1 ) {
			if ( nums * 3 <= 1000000 && ( counts[nums * 3] == -1 || counts[ nums * 3 ] > counts[nums] + 1 ) )
				counts[ nums * 3] = counts[nums] + 1

			if ( nums * 2 <= 1000000 && ( counts[nums * 2] == -1 || counts[ nums * 2 ] > counts[nums] + 1 ) )
				counts[nums * 2] = counts[nums] + 1

			if ( nums + 1 <= 1000000 && ( counts[nums + 1] == -1 || counts[ nums + 1 ] > counts[nums] + 1 ) )
				counts[ nums + 1] = counts[nums] + 1
		}
		//for ( i in 1 .. N )
		//	print("${counts[i]} ")
		//println()
		return counts[ N ]
	}
}