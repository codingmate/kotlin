package algorithm.baekjoon.dp

fun main() {
	val NK = readln().split(" ").map { it.toInt() }
	print( Q12865().solution(NK[0], NK[1]) )
}

class Q12865 {

	fun solution(N: Int, K: Int): Int {
		var result = 0
		data class Weight( var w: Int, var v: Int ) {}

		val weights = Array(N) { Weight(0, 0) }
		for ( weight in weights ) {
			val wv = readln().split(" ").map { it.toInt() }
			weight.w = wv[0]
			weight.v = wv[1]
		}

		weights.sortWith { w1, w2 ->
			if ( w1.w == w2.w )
				w1.v - w2.v
			else
				w1.w - w2.w
		}

		val dp = Array(N) { Weight(0, 0) }

		for ( i in 0 until N ) {
			val weight = weights[i]
			if ( weight.w <= K )
				dp[i].w = weight.w

			for ( j in 0 until i ) {
				if ( dp[j].w + weight.w <= K ) {
					// ing
				}
			}
		}

		print ( weights.joinToString() )
		return result
	}
}