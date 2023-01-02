import java.io.*

fun main() = with(System.`in`.bufferedReader()) {
	val N = readLine()!!.toInt()
	print(Q2748().solution(N))
}

class Q2748 {
    fun solution(N: Int): Long {
        val fibonacci = LongArray(N+1)
        
        if ( N == 1 ) return 1
        fibonacci[1] = 1
        fibonacci[2] = 1

        for ( i in 3 .. N )
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2]
        
        //print(fibonacci.joinToString())
        return fibonacci[N]
    }
}
