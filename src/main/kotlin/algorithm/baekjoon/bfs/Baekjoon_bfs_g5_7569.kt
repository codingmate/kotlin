package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val MNH = readln().split(" ").map{ it.toInt() }
	val C = MNH[0]
	val R = MNH[1]
	val H = MNH[2]

	val space = Array(H) { Array(R) { IntArray(C) } }
	for ( h in 0 until H ) {
		for ( r in 0 until R ) {
			val numList = readln().split(" ").map{ it.toInt() }
			for ( c in 0 until C ) {
				space[h][r][c] = numList[c]
			}
		}
	}
	print( Q7569().solution(H, R, C, space) )
}
class Q7569 {
	fun solution(H: Int, R: Int, C: Int, space: Array<Array<IntArray>>): Int {

		data class Dot( val h: Int, val r: Int, val c: Int)
		val visits = Array(H) { Array(R) { BooleanArray(C) } }
		val costMatrix = Array(H) { Array(R) { IntArray(C) } }
		val q = LinkedList<Dot>()
		for ( h in 0 until H )
			for ( r in 0 until R )
				for ( c in 0 until C ) {
					val value = space[h][r][c]
					costMatrix[h][r][c] = value
					visits[h][r][c] = value != 0
					if ( value == 1 )
						q.add(Dot(h, r, c))
				}

		val dh = arrayOf( 1, -1, 0, 0, 0, 0)
		val dr = arrayOf( 0, 0, 1, -1, 0, 0)
		val dc = arrayOf( 0, 0, 0, 0, 1, -1)
		var completeCount = -1
		while ( q.size > 0 ) {
			val from = q[0]
			q.removeFirst()
			val fromCost = costMatrix[from.h][from.r][from.c]

			for ( i in dh.indices ) {
				val nh = from.h + dh[i]
				val nr = from.r + dr[i]
				val nc = from.c + dc[i]
				if ( nh < 0 || nr < 0 || nc < 0 || nh >= H || nr >= R || nc >= C || visits[nh][nr][nc] )
					continue
				val to = Dot(nh, nr, nc)
				q.add(to)
				visits[to.h][to.r][to.c] = true
				val nextCost = fromCost + 1
				costMatrix[to.h][to.r][to.c] = nextCost
			}
		}

		//println()
		//for ( h in 0 until H )
		//	for ( r in 0 until R ) {
		//		println(costMatrix[h][r].joinToString())
		//	}

		for ( h in 0 until H ) {
			for ( r in 0 until R ) {
				for ( c in 0 until C ) {
					val cost = costMatrix[h][r][c]
					when ( cost ) {
						0 -> return -1
						-1 -> continue
						else -> completeCount = maxOf(completeCount, cost)
					}
				}
			}
		}
		return completeCount - 1
	}
}