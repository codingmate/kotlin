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

		for ( r in 0 until R )
			for ( c in 0 until C )
				if ( matrix[r][c] == 1 ) {
					list1.add(Dot(r, c))
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

					val toBreak = fromBreak + matrix[nr][nc]
					if ( toBreak > 1 )
						continue

					val toCost = fromCost + 1
					val to = Dot(nr, nc)
					if ( fromBreak == toBreak && costMatrix[to.r][to.c] > 0 && toCost > costMatrix[to.r][to.c] )
						continue

					q.add(to)
					costMatrix[to.r][to.c] = toCost
					breakMatrix[to.r][to.c] = toBreak
				}
			} // while : q
			//for ( obj in costMatrix ) {
			//	println(obj.joinToString())
			//}
			return costMatrix[R-1][C-1]
		} // fun : bfs

		return bfs()
	}
}