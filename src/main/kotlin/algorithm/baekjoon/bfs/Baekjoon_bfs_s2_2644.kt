package algorithm.baekjoon.bfs

import java.util.LinkedList

fun main() {
	val n = readln().toInt()
	val (t1, t2) = readln().split(" ").map { it.toInt() }
	val m = readln().toInt()
	print( Q2644().solution(n, t1, t2, m) )
}

class Q2644 {
	fun solution(n: Int, t1: Int, t2: Int, m: Int): Int {
		val nodeMap = HashMap<Int, HashSet<Int>>()

		for ( parent in 1 .. n )
			nodeMap[parent] = HashSet()

		for ( rowCount in 0 until m ) {
			val (parent, child) = readln().split(" ").map{ it.toInt() }
			nodeMap[parent]!!.add(child)
			nodeMap[child]!!.add(parent)
		}

		val q = LinkedList<Int>()
		val distMap = IntArray(n + 1)
		q.add(t1)
		distMap[t1] = 1

		while( q.size > 0 ) {
			val from = q[0]
			q.removeFirst()

			for ( to in nodeMap[from]!!) {
				if ( distMap[to] > 0 )
					continue
				if ( to == t2 )
					return distMap[from]
				distMap[to] = distMap[from] + 1
				q.add(to)
			}
		}

		return -1
	}
}