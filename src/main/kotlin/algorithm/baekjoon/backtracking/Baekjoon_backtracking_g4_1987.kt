package algorithm.baekjoon.backtracking

fun main() {
	val RC = readln().split(" ")
	val R = RC[0].toInt()
	val C = RC[1].toInt()
	val matrix = Array(R) { IntArray(C) }

	for ( row in 0 until R )
		matrix[row] = readln().toCharArray().map { it.code - 'A'.code }.toIntArray()

	print( Q1987( R, C, matrix ).solution() )
}

class Q1987 ( val maxR: Int, val maxC: Int, val matrix : Array<IntArray> ) {
	fun solution() : String {
		//data class RC ( val R:Int, val C:Int )

		var maxCount = 0
		var tempCount = 1

		val dR = arrayOf(1, -1, 0, 0)
		val dC = arrayOf(0, 0, 1, -1)

		val visits = BooleanArray(26)
		visits[matrix[0][0]] = true

		fun backtracking( depth: Int, prevR: Int, prevC:Int ) {

			maxCount = maxOf(maxCount, tempCount)
			if ( depth == 26 )
				return

			for ( i in 0 until 4 ) {
				val nextR = prevR + dR[i]
				val nextC = prevC + dC[i]

				if( !(( nextR < 0 || nextR >= maxR
						 || nextC < 0 || nextC >= maxC ) || visits[matrix[nextR][nextC]])) {
					val nextChar = matrix[nextR][nextC]
					visits[nextChar] = true
					tempCount++
					backtracking(depth + 1, nextR, nextC)
					tempCount--
					visits[nextChar] = false
				}
			} // for : i
		}
		backtracking(1, 0, 0)
		return maxCount.toString()
	}

}
