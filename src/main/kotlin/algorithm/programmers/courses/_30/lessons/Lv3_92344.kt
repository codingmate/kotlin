package algorithm.programmers.courses._30.lessons

fun main() {
	val R = readln().toInt()
	val C = readln().toInt()
	val skillCount = readln().toInt()

	val board = Array(R) { IntArray (C) }
	for ( row in 0 until R )
		board[row] = readln().split(" ").map { it.toInt() }.toIntArray()


	val skills = Array(skillCount) { IntArray(6) }
	for ( row in 0 until skillCount)
		skills[row] = readln().split(" ").map { it.toInt() }.toIntArray()

	print( Q92344().solution(board, skills) )
}

class Q92344 {
	fun solution( board: Array<IntArray>, skill: Array<IntArray> ): Int {
		var answer = 0

		val R = board.size
		val C = board[0].size

		for ( s in skill ) {

			val isAttack = s[0] == 1
			val degree = s[5]

			for ( r in 0 until R ) {
				for ( c in 0 until C ) {
					if ( r >= s[1] && r <= s[3]
							&& c >= s[2] && c <= s[4] )
					board[r][c] += if ( isAttack ) -1 * degree
					             else degree
				} // c
			} // r
		} // s
		for ( obj in board )
			for ( col in obj )
				if ( col >= 1 )
					answer++
		return answer
	}
}

