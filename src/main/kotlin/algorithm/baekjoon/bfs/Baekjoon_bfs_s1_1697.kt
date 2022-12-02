package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val NK = readln().split(" ").map{ it.toInt() }
	val N = NK[0]
	val K = NK[1]
	print(Q1697().solution(N, K))
}

class Q1697 {
	fun solution(N: Int, K: Int): Int {

		val q = LinkedList<Int>()
		q.add(N)
		val costs = Array(100000 + 1) { -1 }
		costs[N] = 0

		while ( q.size > 0 ) {
			val from = q[0]
			q.removeFirst()
			val nextCost = costs[from] + 1
			if ( costs[K] != -1 )
				break
			if ( from - 1 == K || from + 1 == K || 2 * from == K ) {
				costs[K] = nextCost
				break
			}

			if ( from >= 1 && costs[from-1] == -1 ) {
				q.add(from - 1)
				costs[from - 1] = nextCost
			}
			if ( from <= 99999 && costs[from+1] == -1 ) {
				q.add(from + 1)
				costs[from + 1] = nextCost
			}
			if ( from <= 50000 && costs[2 * from] == -1 ) {
				q.add(2 * from)
				costs[2 * from] = nextCost
			}
		}

		return costs[K]
	}

}