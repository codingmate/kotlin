package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val RC = readln().split(" ").map { it.toInt() }
	val R = RC[0]
	val C = RC[1]
	val matrix = Array(R){ IntArray(C) }

	for ( r in 0 until R ) {
		val chars = readln().toCharArray()
		for ( c in 0 until C )
			matrix[r][c] = chars[c].code - '0'.code
	}

	print( Q2206().solution(R, C, matrix) )
}
class Q2206 {
	fun solution( R: Int, C: Int, matrix:Array<IntArray> ): Int {
		data class Dot(val r: Int, val c: Int)

		val dr = arrayOf(0, 0, 1, -1)
		val dc = arrayOf(1, -1, 0, 0)

		fun noBreakBfs(): Int{
			val visits = Array(R) { BooleanArray(C) }
			val costMatrix = Array(R) { IntArray(C) }
			val q = LinkedList<Dot>()
			q.add(Dot(0, 0))
			visits[0][0] = true
			costMatrix[0][0] = 1
			costMatrix[R-1][C-1] = -1

			while ( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()

				for ( i in dr.indices ) {
					val nR = from.r + dr[i]
					val nC = from.c + dc[i]
					if ( nR < 0 || nC < 0 || nR >= R || nC >= C || visits[nR][nC] || matrix[nR][nC] == 1)
						continue
					val to = Dot(nR, nC)
					q.add(to)
					costMatrix[to.r][to.c] = costMatrix[from.r][from.c] + 1
					visits[to.r][to.c] = true
				}
			}
			return costMatrix[R-1][C-1]
 		}
		fun bfs(): Int{
			val costMatrix = Array(R) { IntArray(C) }
			val breakMatrix = Array(R) { IntArray(C) }
			costMatrix[0][0] = 1
			costMatrix[R-1][C-1] = -1
			val q = LinkedList<Dot>()

			q.add(Dot(0, 0))
			while ( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()
				val fromCost = costMatrix[from.r][from.c]
				val fromBreak = breakMatrix[from.r][from.c]
				for ( i in dr.indices ) {
					val nr = from.r + dr[i]
					val nc = from.c + dc[i]
					if ( nr < 0 || nc < 0 || nr >= R || nc >= C )
						continue

					if ( fromBreak + matrix[nr][nc] > 1 )
						continue

					val to = Dot(nr, nc)
					if ( fromBreak == breakMatrix[to.r][to.c] && costMatrix[to.r][to.c] > 0 && fromCost + 1 > costMatrix[to.r][to.c] )
						continue

					q.add(to)
					val toBreak = fromBreak + matrix[nr][nc]
					costMatrix[to.r][to.c] = fromCost + 1
					breakMatrix[to.r][to.c] = toBreak
				}
				for ( r in 0 until R )
					println("${costMatrix[r].joinToString()} || ${breakMatrix[r].joinToString()}")
				println()
			} // while : q

			return costMatrix[R-1][C-1]
		} // fun : bfs

		var minDist = noBreakBfs()
		val dist = bfs()

		when {
			minDist > 0 && dist > 0 -> minDist = minOf(minDist, dist)
			minDist == -1 -> minDist = dist
		}



		return minDist
	}
}