package algorithm.common

fun String.removeLastNewLine() : String {
    if ( this.length > 1)
        return this.substring(0, this.lastIndexOf("\n"))
    else
        return this
}