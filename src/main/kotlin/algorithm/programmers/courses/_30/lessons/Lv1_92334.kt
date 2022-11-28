package algorithm.programmers.courses._30.lessons

class Solution {
	fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {

		val answer = IntArray(id_list.size)

		val reportMatrix = Array(id_list.size) { IntArray(id_list.size) }
		val id_indexMap = HashMap<String, Int>()
		for ( id in id_list )
			id_indexMap[id] = id_list.indexOf(id)

		for ( R in report ) {
			val AB = R.split(" ")
			val A = AB[0]
			val B = AB[1]

			reportMatrix[id_indexMap[A]!!] [id_indexMap[B]!!] = 1
		}
		val reporteds = IntArray(id_list.size)
		for ( index in id_list.indices)
			for ( r in id_list.indices )
				reporteds[index] += if ( reportMatrix[r][index] == 1 ) 1 else 0

		for ( index in reporteds.indices )
			if ( reporteds[index] >= k )
				for ( row in reportMatrix.indices )
					if ( reportMatrix[row][index] == 1 )
						answer[row]++

		return answer
	}
}

fun main() {

}
