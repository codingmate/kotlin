package algorithm.baekjoon.bfs

import java.util.LinkedList
import kotlin.math.abs

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
		data class Dot(var r: Int, var c: Int, var size: Int)

		val fishs = LinkedList<Dot>()

		val R = N
		val C = N
		var shark = Dot(0, 0, 0)

		for ( r in 0 until R )
			for ( c in 0 until C )
				when (val value = matrix[r][c]) {
					9 -> {
						shark = Dot(r, c, 2)
						matrix[r][c] = 0
					}
					0 -> {}
					else -> fishs.add( Dot( r, c, value ) )
				}

		var eatCount = 0
		var totalDistance = 0

		val dr = arrayOf(-1, 0, 0, 1)
		val dc = arrayOf(0, -1, 1, 0)
		while (fishs.any { it.size < shark.size }) {

			fishs.sortWith{ f1, f2 ->
				val f1Dist = abs(f1.r - shark.r) + abs(f1.c - shark.c)
				val f2Dist = abs(f2.r - shark.r) + abs(f2.c - shark.c)
				if ( f1Dist == f2Dist )
					if ( f1.r - f2.r == 0 )
						f1.c - f2.c
					else
						f1.r - f2.r
				else
					f1Dist - f2Dist
			}
			for ( fish in fishs )
				if ( fish.size < shark.size ) {
					val q = LinkedList<Dot>()
					val visits = Array(R) { BooleanArray(C) }
					val costs = Array(R) { IntArray(C) }
					visits[shark.r][shark.c] = true

					q.add(shark)
					while ( q.size > 0 ) {
						val from = q[0]
						q.removeFirst()
						for ( i in dr.indices ) {
							val nextR = from.r + dr[i]
							val nextC = from.c + dc[i]
							if ( nextR < 0 || nextC < 0 || nextR >= R || nextC >= C || shark.size < matrix[nextR][nextC] )
								continue
							visits[nextR][nextC] = true
							costs[nextR][nextC] = costs[from.r][from.c] + 1
							q.add(Dot(nextR, nextC, 0))
						}
						if ( costs[fish.r][fish.c] > 0 ) {
							totalDistance += costs[fish.r][fish.c]
							shark.r = fish.r
							shark.c = fish.c
							fishs.remove(fish)
							println("$shark :: $fish")
							eatCount++
							if ( eatCount == shark.size ) {
								shark.size++
								println("changed !!! :: $shark")
								eatCount = 0
							}
							break
						} // if
					} // while
				} // if
		} // while

		return totalDistance
	}
}