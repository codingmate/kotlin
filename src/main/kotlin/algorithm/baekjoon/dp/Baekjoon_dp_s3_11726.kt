package algorithm.baekjoon.dp

fun main() {
	val n = readln().toInt()
	print( Q11726().solution(n) )
}

class Q11726 {
	fun solution(n: Int): Int {
		if ( n <= 2 )
			return n

		val f = IntArray(n + 1)
		f[1] = 1
		f[2] = 2

		for ( num in 3 .. n )
			f[num] = ( f[num - 1] % 10007 + f[num - 2] % 10007 ) % 10007

		return f[n]
	}
}