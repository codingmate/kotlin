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
		val O_indexs = Array(OList.size) { -1 }
		fun backtracking( depth : Int ) {

			if ( depth == OList.size) {
				for ( obj in sdoku )
					result.append( obj.joinToString(separator = " ", postfix = "\n") )
				return
			}
			val start = if ( depth == 0 ) 0 else O_indexs[depth - 1] + 1

			for ( next in start until OList.size ) {

				val nextRC = OList[next]

				if ( depth + 1 < OList.size )
					O_indexs[depth + 1] = next
				var executeCount = 0
				for ( num in 1 .. 9 ) {
					if ( groupNums[nextRC.R / 3][nextRC.C / 3][num]
							|| rowNums[nextRC.R][num]
							|| colNums[nextRC.C][num] )
						continue
					executeCount++
					val temp = sdoku[nextRC.R][nextRC.C]

					sdoku[nextRC.R][nextRC.C] = num
					groupNums[nextRC.R / 3][nextRC.C / 3][num] = true
					rowNums[nextRC.R][num] = true
					colNums[nextRC.C][num] = true

					backtracking(depth + 1)

					sdoku[nextRC.R][nextRC.C] = temp
					groupNums[nextRC.R / 3][nextRC.C / 3][num] = false
					rowNums[nextRC.R][num] = false
					colNums[nextRC.C][num] = false
				} // for : num
				if (executeCount == 0)
					return

			} // for : next
		}

		backtracking(0)
		return result.toString()
	}

}