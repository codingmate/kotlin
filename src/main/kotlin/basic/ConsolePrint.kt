package basic

class ConsolePrint{

}
fun main(args:Array<String>) {

    val printList = listOf( "1. print : Hello World!\n"
                          , "2. println : Dreams Come true!"
                          , "3. Like a printf(\"%s\")".format("Time FLies Like An Arrow"))
    print( printList[0] )

    println( printList[1] )

    print( printList[2] )
    /*
        print : Hello World!
        println : Dreams Come true!
        Like a printf("Time FLies Like An Arrow")
     */
}


