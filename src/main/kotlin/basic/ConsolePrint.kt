package basic

class ConsolePrint{

}
fun main(args:Array<String>) {

    val printList = listOf( "print : Hello World!\n"
                          , "println : Dreams Come true!"
                          , "Like a printf(\"%s\")".format("Time FLies Like An Arrow"))
    print( printList[0] )

    println( printList[1] )

    print( printList[2] )
}


