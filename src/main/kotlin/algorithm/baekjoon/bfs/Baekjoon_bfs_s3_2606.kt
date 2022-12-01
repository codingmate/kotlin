package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val N = readln().toInt()
	val M = readln().toInt()
	val matrix = Array(N + 1) { IntArray(N + 1) }

	for ( row in 0 until M ) {
		val rc = readln().split(" ")
		val r = rc[0].toInt()
		val c = rc[1].toInt()
		matrix[r][c] = 1
		matrix[c][r] = 1
	}
	print( Q2606().solution(N, M, matrix) )

}

class Q2606 {
	fun solution(N:Int, M:Int, matrix: Array<IntArray>): Int {
		val q = LinkedList<Int>()
		q.add(1)
		val visits = BooleanArray(N + 1)

		while( q.size > 0 ) {
			val from = q[0]
			q.removeFirst()
			visits[from] = true

			for ( to in 2 .. N )
				if ( matrix[from][to] == 0 || visits[to] )
					continue
				else
					q.add(to)
		}
		var count = 0
		for ( num in 2..N )
			if ( visits[num] )
				count++

		return count
	}

}