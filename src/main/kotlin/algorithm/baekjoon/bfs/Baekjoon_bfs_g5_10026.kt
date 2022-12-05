package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val R = readln().toInt()
	val C = R
	val matrix = Array(R) { CharArray(C) }
	for ( r in 0 until R ) {
		val alphabets = readln().toCharArray()
		for ( c in alphabets.indices )
			matrix[r][c] = alphabets[c]
	}
	print( Q10026().solution(R, C, matrix) )
}

class Q10026 {
	fun solution( R: Int, C: Int, matrix: Array<CharArray> ): String {

		data class Dot(val r: Int, val c: Int)
		val dr = arrayOf(0, 0, -1, 1)
		val dc = arrayOf(-1, 1, 0, 0)
		var rgbCount = 0
		var rbCount = 0

		val rbMatrix = Array(R) { CharArray(C) }
		val rgbMatrix = Array(R) { CharArray(C) }


		val q = LinkedList<Dot>()

		for ( r in 0 until R )
			for ( c in 0 until C ) {
				val color = matrix[r][c]
				rbMatrix[r][c] = if ( color == 'G' ) 'R' else color
				rgbMatrix[r][c] = color
			}

		val rgb_visits = Array(R) { BooleanArray(C) }
		for ( r in 0 until R )
			for ( c in 0 until C )
				if ( rgb_visits[r][c] )
					continue
				else {
					rgbCount++
					q.add(Dot(r, c))
					rgb_visits[r][c] = true
					while ( q.size > 0 ) {
						val from = q[0]
						q.removeFirst()

						for ( i in dr.indices ) {
							val nR = from.r + dr[i]
							val nC = from.c + dc[i]
							if ( nR < 0 || nC < 0 || nR >= R || nC >= C || rgb_visits[nR][nC] )
								continue
							val to = Dot(nR, nC)
							if ( rgbMatrix[from.r][from.c] != rgbMatrix[to.r][to.c] )
								continue
							rgb_visits[to.r][to.c] = true
							q.add(to)
						}
					} // while
				} // else

		val rb_visits = Array(R) { BooleanArray(C) }

		for ( r in 0 until R )
			for ( c in 0 until C )
				if ( rb_visits[r][c] )
					continue
				else {
					rbCount++
					rb_visits[r][c] = true
					q.add(Dot(r, c))
					while ( q.size > 0 ) {
						val from = q[0]
						q.removeFirst()

						for ( i in dr.indices ) {
							val nR = from.r + dr[i]
							val nC = from.c + dc[i]
							if ( nR < 0 || nC < 0 || nR >= R || nC >= C || rb_visits[nR][nC] )
								continue
							val to = Dot(nR, nC)
							if ( rbMatrix[from.r][from.c] != rbMatrix[to.r][to.c] )
								continue
							rb_visits[to.r][to.c] = true
							q.add(to)
						}
					} // while
				} // else



		return "$rgbCount $rbCount"
	}
}