package algorithm.baekjoon.bfs

import java.util.*

fun main() {
	val NM = readln().split(" ")
	val N = NM[0].toInt()
	val M = NM[1].toInt()
	val matrix = Array(N){ IntArray(M) }

	for ( row in 0 until N ) {
		val numChars = readln().toCharArray()
		for ( col in 0 until M )
			matrix[row][col] = numChars[col].code - '0'.code
	}

	print( Q2178().solution(N, M, matrix).toString() )
}

class Q2178 {
	fun solution( N:Int, M:Int, matrix:Array<IntArray> ) : Int {

		val visits = Array(N){ BooleanArray(M) }
		for ( row in 0 until N )
			for ( col in 0 until M )
				visits[row][col] = matrix[row][col] == 0

		val dr = arrayOf(1, -1, 0, 0)
		val dc = arrayOf(0, 0, 1, -1)

		val q = LinkedList<Dot>()
		q.add(Dot(0, 0))
		val costMatrix = Array(N) { IntArray(M) }
		costMatrix[0][0] = 1
		visits[0][0] = true

		fun bfs() {

			while( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()

				for ( i in 0 until 4 ) {
					val nextR = from.r + dr[i]
					val nextC = from.c + dc[i]

					val fromCost = costMatrix[from.r][from.c]
					if ( nextR > -1 && nextR < N
							&& nextC > -1 && nextC < M
							&& !visits[nextR][nextC] ) {
						val to = Dot(nextR, nextC)
						visits[to.r][to.c] = true
						costMatrix[to.r][to.c] = fromCost + 1
						q.add(to)
					}
				}
			} // while
		} // bfs
		bfs()

		return costMatrix[N-1][M-1]
	}
	data class Dot( val r: Int, val c: Int)
}
