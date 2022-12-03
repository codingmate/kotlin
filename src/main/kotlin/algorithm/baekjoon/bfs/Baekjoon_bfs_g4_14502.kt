package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val RC = readln().split(" ").map{ it.toInt() }
	val R = RC[0]
	val C = RC[0]

	val matrix = Array(R){ IntArray(C) }
	for ( row in 0 until R )
		readln().split(" ").map { it.toInt() }.forEachIndexed { col, num -> matrix[row][col] = num }

	Q14502().solution(R, C, matrix)

}
class Q14502 {
	fun solution(R: Int, C: Int, matrix:Array<IntArray>): Int{
		data class Dot(val r: Int, val c: Int)

		val dr = arrayOf(0, 0, -1, 1)
		val dc = arrayOf(-1, 1, 0, 0)
		val bfs_visits = Array(R){ BooleanArray(C) }
		fun bfs( start: Dot ) {
			val q = LinkedList<Dot>()
			q.add(start)
			bfs_visits[start.r][start.c] = true
			while( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()

				for ( i in dr.indices ) {
					val nR = from.r + dr[i]
					val nC = from.c + dc[i]
					if ( nR < 0 || nC < 0 || nR >= R || nC >= C || bfs_visits[nR][nC])
						continue
					val to = Dot(nR, nC)
					q.add(to)
					bfs_visits[nR][nC] = true
				} // for : dr.indices
			} // while : q.size
		} // fun : bfs

		val dfs_visits = Array(R){ BooleanArray(C) }
		val indexs = IntArray(3)
		val list0 = ArrayList<Dot>()
		val list2 = ArrayList<Dot>()
		val O_indexs = IntArray(3)
		for ( r in 0 until R )
			for ( c in 0 until C )
				when ( matrix[r][c] ) {
					0 -> list0.add(Dot(r, c))
					2 -> list2.add(Dot(r, c))
				}
		val copyMatrix = Array(R) { IntArray(C) }
		for ( r in 0 until R )
			for ( c in 0 until C )
				copyMatrix[r][c] = matrix[r][c]

		fun dfs( depth: Int) {
			if ( depth == 3 ) {
				return
			}

			val nextStart = if ( depth == 0 ) 0 else indexs[depth-1] + 1
			for ( next in nextStart until list0.size )
			dfs(depth + 1)
		}

		dfs(0)


		return 1
	}
}