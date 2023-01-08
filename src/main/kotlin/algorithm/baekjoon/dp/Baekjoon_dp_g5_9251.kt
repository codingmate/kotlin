package algorithm.baekjoon.dp

fun main() {
	print( Q9251().solution(readln(), readln()) )
}

class Q9251 {
	fun solution(S1: String, S2: String): Int {

		val dp1 = ArrayList<String>()
		val set1 = HashSet<String>()
		dp1.add("")

		for ( c in S1.toCharArray() ) {
			val size = dp1.size
			for ( i in 0 until size ) {
				val str = "${dp1[i]}${c}"
				if ( !set1.contains( str ) ) {
					set1.add(str)
					dp1.add( str )
				}
			}
		}

		val dp2 = ArrayList<String>()
		val set2 = HashSet<String>()
		dp2.add("")

		for ( c in S2.toCharArray() ) {
			val size = dp2.size
			for ( i in 0 until size ) {
				val str = "${dp2[i]}${c}"
				if ( !set2.contains( str ) ) {
					set2.add(str)
					dp2.add( str )
				}
			}
		}

		var max = 0
		for ( s1 in set1 )
			for ( s2 in set2 )
				if ( s1.length > max && s1 == s2 ) {
					max = s1.length
					//println( "max : ${max}, s1 = s2 : ${s1}" )
				}
		return max
	}
}