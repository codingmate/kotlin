package basic

fun main(args:List<String>) {

    val printList = listOf( "print : Hello World!"
                          , "println : Dreams Come true!"
                          , "\n printf(\"%s\")".format("Time FLies Like An Arrow"))
    print( printList[0] )

    println( printList[1] )

    print( printList[2] )
}


