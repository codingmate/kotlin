package algorithm.baekjoon.bfs

fun main() {
	val MNH = readln().split(" ").map{ it.toInt() }
	val C = MNH[0]
	val R = MNH[1]
	val H = MNH[2]

	val space = Array(H) { Array(R) { IntArray(C) } }
	for ( h in 0 until H ) {
		for ( r in 0 until R ) {
			val numList = readln().split(" ").map{ it.toInt() }
			for ( c in 0 until C ) {
				space[h][r][c] = numList[c]
			}
		}
	}
	Q7569().solution()
}
class Q7569 {
	fun solution(): Int {
		return 1
	}
}