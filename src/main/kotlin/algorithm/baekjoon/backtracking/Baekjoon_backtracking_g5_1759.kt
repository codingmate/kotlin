package algorithm.baekjoon.backtracking

fun main() {
    val LC = readln().split(" ")
    val L = LC[0].toInt()
    val C = LC[1].toInt()
    val charList = readln().split(" ").map { it.first() } as ArrayList<Char>
    print( Q1759(L, C, charList).solution() )
}
class Q1759 (val L : Int, val C : Int, val charList : List<Char> ) {
    fun solution() : String {
        val result = StringBuilder()
        val indexs = Array(L) { -1 }
        val charListASC = charList.sorted()
        val aeiouList = listOf('a', 'e', 'i', 'o', 'u')

        fun backtracking(depth: Int) {
            if ( depth == L ) {
                var aeiouCount = 0
                var otherCount = 0
                for ( index in indexs )
                    if ( aeiouList.contains(charListASC[index]) )
                        aeiouCount++
                    else
                        otherCount++
                if ( aeiouCount >= 1
                    && otherCount >= 2 ) {
                    val line = StringBuilder()
                    for ( index in indexs )
                        line.append(charListASC[index])
                    result.append(line.toString() + "\n")
                }
                return
            }
            val nextStartIndex = if ( depth > 0 ) indexs[depth - 1] + 1
                                 else 0
            for ( next in nextStartIndex until C ) {
                indexs[depth] = next
                backtracking(depth + 1)
            }
        }
        backtracking(0)

        return result.toString()
    }
}