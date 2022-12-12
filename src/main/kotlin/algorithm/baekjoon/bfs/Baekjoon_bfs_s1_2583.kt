package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val MNK = readln().split(" ").map{ it.toInt() }
	val M = MNK[0]
	val N = MNK[1]
	val K = MNK[2]

	print(Q2583().solution(M, N, K))
}

class Q2583 {
	fun solution(Y: Int, X: Int, K: Int ): String {
		data class Dot(val x: Int, val y: Int)

		val visits = Array(X + 1) { BooleanArray(Y + 1) }
		for ( k in 0 until K ) {
			val (x1, y1, x2, y2) = readln().split(" ").map{ it.toInt() }
			for ( x in x1 until x2 )
				for ( y in y1 until y2 ){
					visits[x][y] = true
					//println()
					//for ( obj in visits )
					//	println(obj.joinToString())
				}

		}

		val countList = ArrayList<Int>()

		val q = LinkedList<Dot>()
		val dx = arrayOf(0, 0, -1, 1)
		val dy = arrayOf(-1, 1, 0, 0)
		for ( x in 0 until X )
			for ( y in 0 until Y )
				if ( visits[x][y] )
					continue
				else {
					var count = 0
					visits[x][y] = true
					q.add(Dot(x, y))
					while ( q.size > 0 ) {
						count++
						val from = q[0]
						q.removeFirst()

						for ( i in dx.indices ) {
							val nX = from.x + dx[i]
							val nY = from.y + dy[i]
							if ( nX < 0 || nY < 0 || nX >= X || nY >= Y || visits[nX][nY] )
								continue
							visits[nX][nY] = true
							q.add(Dot(nX, nY))
						} // for
					} // while
					countList.add(count)
				} // else


		countList.sort()
		return countList.joinToString(separator = " ")
	}
}