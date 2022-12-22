package algorithm.baekjoon.dp

fun main() {
	print( Q2579().solution(readln().toInt()) )
}

class Q2579 {
	fun solution(floorCount: Int): Int {

		val scores = IntArray(floorCount + 1)
		val sums = IntArray(floorCount + 1)

		for ( floor in 1 .. floorCount )
			scores[floor] = readln().toInt()

		if ( floorCount == 1 )
			return scores[1]
		if ( floorCount == 2)
			return scores[1] + scores[2]

		sums[1] = scores[1]
		sums[2] = scores[1] + scores[2]

		for ( floor in 3 ..floorCount ) {
			sums[floor] = scores[floor]
			sums[floor] += maxOf( sums[floor - 3] + scores[floor - 1], sums[floor - 2])
		}

		return sums[floorCount]
	}
}