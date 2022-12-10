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
		if ( R == 1 && C == 1 )
			return 1
		val dr = arrayOf(0, 0, 1, -1)
		val dc = arrayOf(1, -1, 0, 0)

		val costs = Array(R) { IntArray(C) }
		val walls = Array(R) { IntArray(C) }

		val q = LinkedList<Dot>()
		q.add(Dot(0, 0))
		costs[0][0] = 1

		while( q.size > 0 ) {
			val from = q[0]
			q.removeFirst()


			for ( i in dr.indices ) {
				val toR = from.r + dr[i]
				val toC = from.c + dc[i]

				if ( toR == R - 1 && toC == C - 1 )
					return costs[from.r][from.c] + 1

				if ( toR < 0 || toC < 0 || toR >= R || toC >= C
						|| walls[from.r][from.c] + matrix[toR][toC] > 1 )
					continue

				if ( toR == R - 1 && toC == C - 1 )
					return costs[from.r][from.c] + 1

				costs[toR][toC] = costs[from.r][from.c] + 1

				walls[toR][toC] = walls[from.r][from.c] + matrix[toR][toC]
				for ( r in 0 until R )
					println("${costs[r].joinToString()} ::: ${walls[r].joinToString()} ")
				q.add(Dot(toR, toC))

			}
		}


		return -1
	}
}