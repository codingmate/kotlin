package algorithm.baekjoon.dp
fun main() {
	print( Q1149().solution(readln().toInt()) )
}
class Q1149 {
	fun solution(N : Int): Int {

		val startNums = readln().split(" ").map{ it.toInt() }
		var lastR = startNums[0]
		var lastG = startNums[1]
		var lastB = startNums[2]

		for ( row in 2 .. N ) {
			val RGB = readln().split(" ").map{ it.toInt() }
			val minRG = minOf(lastR, lastG)
			val minGB = minOf(lastG, lastB)
			val minBR = minOf(lastB, lastR)

			lastR = RGB[0] + minGB
			lastG = RGB[1] + minBR
			lastB = RGB[2] + minRG
		}

		return minOf(lastR, lastG, lastB)
	}
}