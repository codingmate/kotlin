package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val N = readln().toInt()
	val matrix = Array(N) { IntArray(N) }
	for ( r in 0 until N ) {
		val numList = readln().split(" ").map { it.toInt() }
		for ( c in 0 until N )
			matrix[r][c] = numList[c]
	}
	print( Q2468().solution(N, matrix) )
}

class Q2468 {
	fun solution(N: Int, matrix:Array<IntArray>): Int {
		data class Dot(val r: Int, val c: Int)
		val R = N
		val C = N
		var maxCount = Int.MIN_VALUE

		val dr = arrayOf( 0, 0, 1, -1 )
		val dc = arrayOf( 1, -1, 0, 0 )
		val q = LinkedList<Dot>()
		for ( h in 1 until 100 ) {
			val visits = Array(R) { BooleanArray(C) }
			for( r in 0 until R)
				for( c in 0 until C)
					visits[r][c] = matrix[r][c] <= h

			var count = 0
			for( r in 0 until R)
				for( c in 0 until C)
					if ( visits[r][c] )
						continue
					else {
						count++
						q.add(Dot(r, c))
						visits[r][c] = true
						while( q.size > 0 ) {
							val from = q[0]
							q.removeFirst()

							for ( i in dr.indices ) {
								val nR = from.r + dr[i]
								val nC = from.c + dc[i]
								if ( nR < 0 || nC < 0 || nR >= R || nC >= C || visits[nR][nC] || matrix[nR][nC] <= h )
									continue
								val to = Dot(nR, nC)
								q.add(to)
								visits[to.r][to.c] = true
							}
						}
					}
			maxCount = maxOf( maxCount, count)
		}
		return if ( maxCount == 0 ) 1 else maxCount
	}
}