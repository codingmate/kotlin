package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val NM = readln().split(" ").map { it.toInt() }
	val N = NM[0]
	val M = NM[1]

	val graph = Array(N + 1){ BooleanArray(N + 1) }
	for ( rowCount in 0 until M ) {
		val node = readln().split(" ").map { it.toInt() }
		val from = node[0]
		val to = node[1]
		graph[from][to] = true
		graph[to][from] = true
	}
	print(Q11724().solution(N, M, graph))
}

class Q11724 {
	fun solution( N: Int, M:Int, graph: Array<BooleanArray> ): Int {
		val visits = BooleanArray(N + 1)
		val q = LinkedList<Int>()
		var count = 0
		for ( num in 1 .. N )
			if ( visits[num] )
				continue
			else {
				q.add(num)
				visits[num] = true
				count++
				while ( q.size > 0 ) {
					val from = q[0]
					q.removeFirst()

					for ( to in 1 .. N )
						if ( graph[from][to] && !visits[to] ) {
							q.add(to)
							visits[to] = true
						}
				} // while
			} // else in for
		return count
	}
}