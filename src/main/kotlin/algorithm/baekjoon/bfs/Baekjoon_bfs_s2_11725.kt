package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val N = readln().toInt()
	print(Q11725().solution(N))
}
class Q11725 {
	fun solution(N: Int): String {
		val graph = HashMap<Int, HashSet<Int>>()
		for ( n in 1 .. N )
			graph[n] = HashSet()

		for ( n in 0 until N - 1 ) {
			val (n1, n2) = readln().split(" ").map{ it.toInt() }
			graph[n1]?.add(n2)
			graph[n2]?.add(n1)
		}

		val parents = IntArray(N + 1 )
		val q = LinkedList<Int>()
		q.add(1)
		parents[1] = 100001

		while( q.size > 0 ) {

			val parent = q[0]
			q.removeFirst()
			for( child in graph[parent]!!) {
				if ( parents[child] > 0 )
					continue
				else {
					q.add(child)
					parents[child] = parent
				}
			} // for
		} // while
		val result = StringBuilder()
		for ( child in 2 .. N )
			result.append("${parents[child]}\n")

		return result.toString()
	}
}