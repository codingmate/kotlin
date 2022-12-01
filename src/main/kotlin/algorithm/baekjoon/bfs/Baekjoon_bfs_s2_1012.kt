package algorithm.baekjoon.bfs

import java.util.LinkedList

val T = readln().toInt()

fun main() {
	print(Q1012().solution())
}

data class Dot( val r: Int, val c: Int )

class Q1012 {
	fun solution(): String {
		val result = StringBuilder()
		val q = LinkedList<Dot>()

		val dr = arrayOf(0, 0, 1, -1)
		val dc = arrayOf(1, -1, 0, 0)

		for ( exe in 0 until T ) {
			val CRK = readln().split(" ").map{ it.toInt() }
			val C = CRK[0]
			val R = CRK[1]
			val K = CRK[2]
			val matrix = Array(R) { IntArray(C) }


			for ( row in 0 until K ) {
				val cr = readln().split(" ").map{ it.toInt() }
				val c = cr[0]
				val r = cr[1]
				matrix[r][c] = 1
			}

			val visits = Array(R) { BooleanArray(C) }
			for ( r in 0 until R )
				for ( c in 0 until C )
					visits[r][c] = matrix[r][c] == 0


			var totalCount = 0
			for ( r in 0 until R ) {
				for ( c in 0 until C ) {
					if ( visits[r][c] )
						continue
					q.add(Dot(r, c))
					visits[r][c] = true
					while ( q.size > 0 ) {
						val from = q[0]
						q.removeFirst()

						for ( i in dr.indices ) {
							val nextR = from.r + dr[i]
							val nextC = from.c + dc[i]
							if ( nextR < 0 || nextC < 0 || nextR > R-1 || nextC > C-1 || visits[nextR][nextC] )
								continue
							visits[nextR][nextC] = true
							val to = Dot(nextR, nextC)
							q.add(to)
						}
					} // while : q
					totalCount++
				} // for : c
			} // for : r
			result.append(totalCount.toString() + "\n")
		} // for : exe
		return result.toString()
	} // fun : solution
}