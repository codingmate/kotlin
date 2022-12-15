package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val N = readln().toInt()
	val matrix = Array(N) { IntArray(N) }
	for ( r in 0 until N ) {
		val obj = readln().split(" ").map { it.toInt() }
		for ( c in 0 until N )
			matrix[r][c] = obj[c]
	}

	print( Q16236().solution(N, matrix) )
}

class Q16236 {
	fun solution( N: Int, matrix: Array<IntArray> ): Int {
		data class Dot(var r: Int, var c: Int)

		val R = N
		val C = N
		val shark = Dot(-1,-1)

		for ( r in 0 until R )
			for ( c in 0 until C )
				if ( matrix[r][c] == 9 ) {
					shark.r = r
					shark.c = c
					matrix[r][c] = 0
				}

		var totalDistance = 0
		var sharkSize = 2
		var eatCount = 0

		val dr = arrayOf(-1, 0, 0, 1) // 상, 좌, 우, 하
		val dc = arrayOf(0, -1, 1, 0)

		var ate = true

		while ( ate ) {
			ate = false

			val q = LinkedList<Dot>()
			val costs = Array(R){ IntArray(C) { -1 } }

			q.add(shark)
			costs[shark.r][shark.c] = 0
			while ( q.size > 0 && !ate ) {
				val from = q[0]
				q.removeFirst()

				if ( matrix[from.r][from.c] in 1 until sharkSize ) {
					eatCount++
					if ( eatCount == sharkSize ) {
						eatCount = 0
						sharkSize++
					}
					totalDistance += costs[from.r][from.c]
					shark.r = from.r
					shark.c = from.c
					matrix[shark.r][shark.c] = 0
					ate = true
					break
				}
				for ( i in dr.indices ) {
					val toR = from.r + dr[i]
					val toC = from.c + dc[i]

					if ( toR < 0 || toC < 0 || toR >= R || toC >= C || costs[toR][toC] > -1 || matrix[toR][toC] > sharkSize )
						continue
					val toCost = costs[from.r][from.c] + 1

					q.add(Dot(toR, toC))
					costs[toR][toC] = toCost
				} // for
				q.sortWith { a1, a2 ->
					if ( costs[a1.r][a1.c] == costs[a2.r][a2.c] ) {
						if ( matrix[a1.r][a1.c] < sharkSize && matrix[a2.r][a2.c] >= sharkSize )
							1
						else if ( matrix[a1.r][a1.c] >= sharkSize && matrix[a2.r][a2.c] < sharkSize )
							-1
						else if ( a1.r == a2.r )
							a1.c - a2.c
						else
							a1.r - a2.r

					} else
						costs[a1.r][a1.c] - costs[a2.r][a2.c]
				}
			} // while
		}
		return totalDistance
	}
}