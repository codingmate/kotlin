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
					9 -> shark = Dot(r, c, 2)
					0 -> {}
					else -> fishs.add( Dot( r, c, value ) )
				}

		var eatCount = 0
		var totalDistance = 0
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
			eatCount++
			if ( eatCount == shark.size ) {
				shark.size++
				eatCount = 0
			}
			var index = 0
			while( fishs[index].size >= shark.size ) {
				index++
			}
			val fish = fishs[index]

			totalDistance += abs(fish.r - shark.r) + abs ( fish.c - shark.c )
			shark.r = fish.r
			shark.c = fish.c
			fishs.removeFirst()
		}

		return totalDistance
	}
}