package algorithm.baekjoon.backtracking

fun main() {
	val matrix = Array(9){ IntArray(9) }
	for ( row in 0 until 9 )
		matrix[row] = readln().split(" ").map{ it.toInt() }.toIntArray()
	print( Q2580(matrix).solution() )
}
class Q2580 ( val sdoku : Array<IntArray> ) {

	fun solution(): String {
		val groupNums = Array(3){ Array(3) { BooleanArray(10) } }
		val rowNums = Array(9) { BooleanArray(10) }
		val colNums = Array(9) { BooleanArray(10) }

		data class RC ( val R: Int, val C: Int )
		val OList = ArrayList<RC>()
		for ( r in 0 until 9 ) {
			for ( c in 0 until 9 ) {
				val num = sdoku[r][c]
				when ( num ) {
					0 -> OList.add( RC( r, c ) )
					else -> {
						groupNums[r / 3][c / 3][num] = true
						rowNums[r][num] = true
						colNums[c][num] = true
					}
				}
			} // for : c
		} // for : r

		val result = StringBuilder()
		var isEnded = false
		fun backtracking( depth : Int ) {

			if ( isEnded )
				return
			if ( depth == OList.size) {
				for ( obj in sdoku )
					result.append( obj.joinToString(separator = " ", postfix = "\n"))
				isEnded = true
				return
			}

			var executeCount = 0
			val nextRC = OList[depth]
			for ( num in 1 .. 9 ) {
				if ( groupNums[nextRC.R / 3][nextRC.C / 3][num]
						|| rowNums[nextRC.R][num]
						|| colNums[nextRC.C][num] )
					continue
				executeCount++

				sdoku[nextRC.R][nextRC.C] = num
				groupNums[nextRC.R / 3][nextRC.C / 3][num] = true
				rowNums[nextRC.R][num] = true
				colNums[nextRC.C][num] = true

				backtracking(depth + 1)

				sdoku[nextRC.R][nextRC.C] = 0
				groupNums[nextRC.R / 3][nextRC.C / 3][num] = false
				rowNums[nextRC.R][num] = false
				colNums[nextRC.C][num] = false
			} // for : num
		}

		backtracking(0)
		return result.toString()
	}

}