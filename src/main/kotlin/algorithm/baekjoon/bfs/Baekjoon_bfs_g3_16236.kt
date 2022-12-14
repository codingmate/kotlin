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

		val R = N
		val C = N
		var shark = Dot(0, 0, 0)
		val fishs = LinkedList<Dot>()
		for ( r in 0 until R )
			for ( c in 0 until C )
				when ( matrix[r][c] ) {
					0    -> {}
					9    -> {
						shark = Dot(r, c, 2)
						matrix[r][c] = 0
					}
					else -> fishs.add(Dot(r, c, matrix[r][c]))
				}


		var totalDistance = 0
		var eatCount = 0
		val dr = arrayOf(-1, 0, 0, 1) // 위, 왼, 우, 밑 순서
		val dc = arrayOf(0, -1, 1, 0)
		while (fishs.any { it.size < shark.size }) { // 먹을 수 있는 물고기가 남아있는가
			val q = LinkedList<Dot>()
			val costs = Array(R) { IntArray(C) { -1 } }
			q.add(shark)
			costs[shark.r][shark.c] = 0

			var foundFish = false
			while ( q.size > 0 && !foundFish ) {

				val from = q[0]
				q.removeFirst()
				for ( i in dr.indices ) {
					val toR = from.r + dr[i]
					val toC = from.c + dc[i]
					if ( toR < 0 || toC < 0 || toR >= R || toC >= C || costs[toR][toC] > -1 || matrix[toR][toC] > shark.size )
						continue
					costs[toR][toC] = costs[from.r][from.c] + 1
					
					if ( matrix[toR][toC] > 0
							&& shark.size > matrix[toR][toC] ) { // 다음 칸에 먹을 수 있는 물고기가 있을 경우
						for ( i in fishs.indices ) { // 먹을 물고기 제거
							val fish = fishs[i]
							if ( fish.r == toR && fish.c == toC ) {
								fishs.remove(fish)
								break
							}
						}
						matrix[toR][toC] = 0
						totalDistance += costs[toR][toC]

						if ( ++eatCount == shark.size ) {
							eatCount = 0
							shark.size++
						}
						shark.r = toR
						shark.c = toC
						foundFish = true
						break
					} else if ( matrix[toR][toC] == 0 )
						q.add(Dot(toR, toC, 0))
				}
			}
		} // while



		return totalDistance
	}
}