package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val RC = readln().split(" ").map{ it.toInt() }
	val R = RC[0]
	val C = RC[1]

	val matrix = Array(R){ IntArray(C) }
	for ( row in 0 until R )
		readln().split(" ").map { it.toInt() }.forEachIndexed { col, num -> matrix[row][col] = num }

	print( Q14502().solution(R, C, matrix) )

}
class Q14502 {
	fun solution(R: Int, C: Int, matrix:Array<IntArray>): Int{
		data class Dot(val r: Int, val c: Int)

		val dr = arrayOf(0, 0, -1, 1)
		val dc = arrayOf(-1, 1, 0, 0)
		val visits = Array(R){ BooleanArray(C) }
		val q = LinkedList<Dot>()
		var maxCount = Int.MIN_VALUE
		fun bfs() {
			val copyVisits = Array(R) { BooleanArray(C) }
			for ( r in 0 until R )
				for ( c in 0 until C )
					copyVisits[r][c] = visits[r][c]
			while( q.size > 0 ) {
				val from = q[0]
				q.removeFirst()

				for ( i in dr.indices ) {
					val nR = from.r + dr[i]
					val nC = from.c + dc[i]
					if ( nR < 0 || nC < 0 || nR >= R || nC >= C || copyVisits[nR][nC] )
						continue
					val to = Dot(nR, nC)
					q.add(to)
					copyVisits[to.r][to.c] = true
				} // for : dr.indices
			} // while : q.size
			var count = 0
			copyVisits.forEach {
				it.forEach {
					if ( !it )
						count++
				}
			}
			maxCount = maxOf(maxCount, count)


		} // fun : bfs



		val wall_indexs = IntArray(3)
		val list0 = ArrayList<Dot>()
		val list2 = ArrayList<Dot>()

		for ( r in 0 until R )
			for ( c in 0 until C ) {
				when ( matrix[r][c] ) {
					0 -> list0.add(Dot(r, c))
					2 -> list2.add(Dot(r, c))
				}
				if ( matrix[r][c] > 0 )
					visits[r][c] = true
			}
		fun dfs( depth: Int) {
			if ( depth == 3 ) {
				wall_indexs.forEach {
					val wall = list0[it]
					visits[wall.r][wall.c] = true
				}

				list2.forEach {
					q.add(it)
				}

				bfs()

				//if ( maxCount == count ) {
				//	println()
				//	for ( obj in visits )
				//		println(obj.joinToString())
				//}

				wall_indexs.forEach {
					val wall = list0[it]
					visits[wall.r][wall.c] = false
				}
				return
			}

			val nextStart = if ( depth == 0 ) 0 else wall_indexs[depth-1] + 1
			for ( next in nextStart until list0.size ) {
				wall_indexs[depth] = next
				dfs(depth + 1)
			}
		}

		dfs(0)

		return maxCount
	}
}
