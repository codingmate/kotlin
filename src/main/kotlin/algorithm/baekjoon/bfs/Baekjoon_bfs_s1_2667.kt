package algorithm.baekjoon.bfs

import java.util.*

fun main() {
	val N = readln().toInt()
	val matrix = Array(N){ IntArray(N) }
	for ( row in 0 until N ) {
		val line = readln().toCharArray()
		for ( col in 0 until N )
			matrix[row][col] = line[col].code - '0'.code
	}


	print( Q2667().solution(N, matrix) )
}

class Q2667 {
	fun solution( N: Int, matrix: Array<IntArray>): String {

		data class Dot(val r: Int, val c: Int)
		val q = LinkedList<Dot>()

		val dr = arrayOf(0, 0, 1, -1)
		val dc = arrayOf(1, -1, 0, 0)

		val visits = Array(N) { BooleanArray(N) }

		for ( r in 0 until N )
			for ( c in 0 until N )
				visits[r][c] = matrix[r][c] == 0

		val counts = ArrayList<Int>()

		fun bfs( start : Dot, cost: Int ) {

			q.add(start)
			visits[start.r][start.c] = true
			var count = 1
			while(q.size > 0) {
				val from = q[0]
				q.removeFirst()

				for ( i in 0 until 4 ) {
					val nR = from.r + dr[i]
					val nC = from.c + dc[i]
					if ( nR < 0 || nC < 0 || nR > N-1 || nC > N-1 || visits[nR][nC] )
						continue
					val to = Dot(nR, nC)
					q.add(to)
					matrix[to.r][to.c] = cost
					visits[to.r][to.c] = true

					count++
				} // for
			} // while
			counts.add(count)
		} // bfs

		var cost = 1
		for ( row in 0 until N )
			for ( col in 0 until N )
				if ( matrix[row][col] == 1 )
					bfs( Dot(row, col), ++cost )

		counts.sort()
		val result = StringBuilder()
		result.append(counts.size.toString())
		for ( count in counts )
			result.append("\n" + count)

		return result.toString()
	}
}
