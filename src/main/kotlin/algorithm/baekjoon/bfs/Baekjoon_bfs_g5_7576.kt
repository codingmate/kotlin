package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val CR = readln().split(" ").map { it.toInt() }
	val C = CR[0]
	val R = CR[1]
	val matrix = Array(R) { IntArray(C) }
	for ( r in 0 until R ) {
		val rNums = readln().split(" ").map{it.toInt()}
		for ( c in 0 until C )
			matrix[r][c] = rNums[c]
	}

	print( Q7576().solution(C, R, matrix) )
}


class Q7576 {
	data class Dot(val r:Int, val c:Int)

	fun solution(C: Int, R: Int, matrix: Array<IntArray>): Int {
		var result = -1

		val costMatrix = Array(R) { IntArray(C) }
		val visits = Array(R) { BooleanArray(C) }

		val q = LinkedList<Dot>()

		for ( r in 0 until R )
			for ( c in 0 until C )
				when ( matrix[r][c] ) {
					1 -> {
						q.add(Dot(r, c))
						visits[r][c] = true
						costMatrix[r][c] = 1
					}
					-1 -> {
						visits[r][c] = true
						costMatrix[r][c] = 0
					}
				}



		val dr = arrayOf(0, 0, 1, -1)
		val dc = arrayOf(1, -1, 0, 0)
		while ( q.size > 0 ) {
			val from = q[0]
			q.removeFirst()

			for ( i in dr.indices ) {
				val nextR = from.r + dr[i]
				val nextC = from.c + dc[i]
				if ( nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || visits[nextR][nextC] )
					continue
				q.add(Dot(nextR, nextC))
				visits[nextR][nextC] = true
				costMatrix[nextR][nextC] = costMatrix[from.r][from.c] + 1
			}
		}
		//for ( obj in matrix )
		//	println(obj.joinToString())
		//println()
		//
		//for ( obj in costMatrix )
		//	println(obj.joinToString())

		for ( r in 0 until R )
			for ( c in 0 until C )
				result = maxOf(result, costMatrix[r][c])


		return result - 1
	}

}