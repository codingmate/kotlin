package algorithm.bfs

import java.util.*

fun main() {
	val NMV = readln().split(" ")
	val N = NMV[0].toInt()
	val M = NMV[1].toInt()
	val V = NMV[2].toInt()
	val graph = Array( N + 1 ) { TreeSet<Int>() }
	for ( row in 0 until M ) {
		val node = readln().split(" ")
		val start = node[0].toInt()
		val end = node[1].toInt()
		graph[start].add(end)
		graph[end].add(start)
	}
	//for ( i in graph.indices ) {
	//	println( graph[i].joinToString() )
	//}

	print( Q1260().solution(N, M, V, graph) )
}


class Q1260 {
	fun solution(N:Int, M:Int, V:Int, graph:Array<TreeSet<Int>>): String {
		var dfsResult = String()
		val bfsResult : String

		val visits = BooleanArray(N + 1)
		val nums = ArrayList<Int>()
		fun dfs( depth: Int ) {
			val start = nums[depth - 1]
			for ( next in graph[start] )
				if ( visits[next] ) {
					continue
				}
				else {
					println("depth : $depth")
					visits[next] = true
					nums.add(next)
					dfs( depth + 1)
				}
		} // fun : dfs

		visits[V] = true
		nums.add(V)
		dfs(1)
		dfsResult = nums.joinToString(separator = " ")

		val q = LinkedList<Int>()
		val numList = ArrayList<Int>()
		q.add(V)
		numList.add(V)

		val visitList = ArrayList<Boolean>(N)
		while( q.size > 0 ) {

			val start = q[0]
			if ( !numList.contains(start) )
				numList.add(start)
			if ( numList.size == N )
				break
			q.removeFirst()

			for ( next in graph[start] )
				q.add(next)
		}
		bfsResult = numList.joinToString(separator = " ")



		return dfsResult + "\n" + bfsResult
	}
}
