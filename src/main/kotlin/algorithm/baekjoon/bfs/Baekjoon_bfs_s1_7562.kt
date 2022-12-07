package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val T = readln().toInt()
	print( Q7562().solution(T) )
}

class Q7562 {
	fun solution(T: Int): String {
		val result = StringBuilder()
		data class Dot(val r: Int, val c: Int)
		val dr = arrayOf(-2, -2, -1, -1, 1, 1, 2, 2)
		val dc = arrayOf(-1, 1, -2, 2, -2, 2, -1, 1)

		for ( repeatCount in 1 .. T ) {
			val R = readln().toInt()
			val C = R
			val q = LinkedList<Dot>()
			val costMatrix = Array(R) { IntArray(C) }
			val startRC = readln().split(" ").map { it.toInt() }
			val knight = Dot(startRC[0], startRC[1])
			val endRC = readln().split(" ").map { it.toInt() }
			val endDot = Dot(endRC[0], endRC[1])
			if ( startRC == endRC ) {
				result.append("0\n")
				continue
			}

			val visits = Array(R) { BooleanArray(C) }
			q.add(Dot(knight.r, knight.c))
			visits[knight.r][knight.c] = true

			var isEnded = false
			while ( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()
				val fromCost = costMatrix[from.r][from.c]
				//println("${from.r}, ${from.c} :: $fromCost")
				for ( i in dr.indices ) {
					val nr = from.r + dr[i]
					val nc = from.c + dc[i]
					if ( nr < 0 || nc < 0 || nr >= R || nc >= C || visits[nr][nc]  )
						continue
					val to = Dot(nr, nc)
					if ( to.r == endDot.r && to.c == endDot.c ) {
						result.append("${fromCost+1}\n")
						isEnded = true
						break
					}
					q.add(to)
					visits[to.r][to.c] = true
					costMatrix[to.r][to.c] = fromCost + 1
				}
				if ( isEnded )
					break
			} // while
		} // for : repeatCount
		return result.toString()
	}
}