package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val Solution = Q4963()
	while ( true ) {
		val CR = readln().split(" ").map { it.toInt() }
		val C = CR[0]
		val R = CR[1]
		if ( C == 0 && R == 0 )
			break

		val matrix = Array(R) { IntArray(C) }

		for ( r in 0 until R ) {
			val numList = readln().split(" ").map { it.toInt() }
			for ( c in 0 until C )
				matrix[r][c] = numList[c]
		}

		println(Solution.solution(R, C, matrix))
	}
}
class Q4963 {
	fun solution( R: Int, C: Int, matrix:Array<IntArray> ): Int {
		data class Dot ( val r: Int, val c: Int)
		val visits = Array(R) { BooleanArray(C) }
		val dr = arrayOf(0, 0, 1, -1, 1, 1, -1, -1)
		val dc = arrayOf(1, -1, 0, 0, 1, -1, 1, -1)

		fun bfs ( start: Dot ) {
			val q = LinkedList<Dot>()
			q.add(start)
			visits[start.r][start.c] = true

			while ( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()
				for ( i in dr.indices ) {
					val nR = from.r + dr[i]
					val nC = from.c + dc[i]
					if ( nR < 0 || nC < 0 || nR >= R || nC >= C || visits[nR][nC] )
						continue
					val to = Dot(nR, nC)
					q.add(to)
					visits[to.r][to.c] = true
				} // for : dr.indices
			} // while
		}

		for ( r in 0 until R )
			for ( c in 0 until C )
				visits[r][c] = matrix[r][c] == 0

		var islandCount = 0
		for ( r in 0 until R )
			for ( c in 0 until C )
				if ( visits[r][c] )
					continue
				else {
					visits[r][c] = true
					bfs(Dot(r, c))
					islandCount++
				}
		return islandCount
	}
}