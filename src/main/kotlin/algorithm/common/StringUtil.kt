package algorithm.common

fun String.removeLastNewLine() : String {
    if ( this.length > 1 && this.lastIndexOf("\n") > -1)
        return this.substring(0, this.lastIndexOf("\n"))
    else
        return this
}

fun <T> Iterable<T>.joinToStringBuilder(separator : CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = ""): StringBuilder {
    val result = StringBuilder(prefix)
    for ( e in this ) {
        result.append(e.toString() + separator)
    }
    result.append(postfix)
    return result
}
fun <T> Array<out T>.joinToStringBuilder(separator : CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = ""): StringBuilder {
    val result = StringBuilder(prefix)
    for ( e in this )
        result.append(e.toString() + separator)
    result.append(postfix)
    return result
}
