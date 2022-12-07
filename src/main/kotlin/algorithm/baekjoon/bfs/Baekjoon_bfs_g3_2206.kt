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
		val list1 = ArrayList<Dot>()
		val visits = Array(R) { BooleanArray(C) }
		for ( r in 0 until R )
			for ( c in 0 until C )
				if ( matrix[r][c] == 1 ) {
					list1.add(Dot(r, c))
					visits[r][c] = true
				}

		fun bfs(): Int{
			val costMatrix = Array(R) { IntArray(C) }
			costMatrix[0][0] = 1
			costMatrix[R-1][C-1] = -1
			val q = LinkedList<Dot>()

			q.add(Dot(0, 0))
			visits[0][0] = true
			while ( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()
				for ( i in dr.indices ) {
					val nr = from.r + dr[i]
					val nc = from.c + dc[i]
					if ( nr < 0 || nc < 0 || nr >= R || nc >= C || visits[nr][nc] )
						continue
					val to = Dot(nr, nc)
					q.add(to)
					visits[to.r][to.c] = true
					costMatrix[to.r][to.c] = costMatrix[from.r][from.c] + 1
				}
			}
			//for ( obj in costMatrix ) {
			//	println(obj.joinToString())
			//}
			return costMatrix[R-1][C-1]
		} // fun : bfs

		var minDist = bfs()

		for ( e1 in list1 ) {
			for ( r in 0 until R )
				for ( c in 0 until C )
						visits[r][c] = matrix[r][c] == 1
			visits[e1.r][e1.c] = false
			val dist = bfs()
			//println(" minDist : $minDist, dist : $dist")
			when {
				minDist == -1 && dist > -1 -> minDist = dist
				minDist > -1 && dist > -1 -> minDist = minOf(minDist, dist)
			}

			visits[e1.r][e1.c] = true
		}

		return minDist
	}
}