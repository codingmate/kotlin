package algorithm.baekjoon.dp

fun main() {
	val T = readln().toInt()
	print(Q2775().solution(T))
}

class Q2775 {
	fun solution(T: Int): String {
		val result = StringBuilder()

		for ( i in 0 until T ) {

			val k = readln().toInt()
			val n = readln().toInt()
			val R = k
			val C = n

			val aparts = Array(R+1) { IntArray( C + 1 ) }

			for ( c in 1 .. C )
				aparts[0][c] = c


			for ( r in 1 .. R ) {
				aparts[r][1] = aparts[r-1][1]
				if ( C > 1 )
					for ( c in 2 .. C )
						aparts[r][c] = aparts[r][c-1] + aparts[r-1][c]
			}
			//for ( obj in aparts )
			//	println( obj.joinToString() )
			result.append("${aparts[R][C]}\n")

		}

		return result.toString()
	}
}