import java.io.*;
import java.util.*;
import kotlin.math.*


/*
You are writing a script that optimizes seating arrangements for a round-table bilateral talk 
between the US and Japan embassy staffs. 
To ensure equal amount of attention and dialog between each individual representative, 
you need to spread the attendees from the two countries as evenly as possible. 



Question part 1: 
write a function that accepts the number of USA representative and the number of japan representatives, 
returns the seating solution. 
Assume number of US representatives is divisible by number of Japan representatives. (U % J == 0)

Example: 8 USA representatives, 4 Japan representatives. 

Answer: U, U, J, U, U, J, U, U, J, U, U, J


Modify your solution in part 1 so that 
it will produce a solution regardless of whether one input is divisible by the other. 

Example:  6U, 4J 

INVALID: U, U, J, U, U, J, U, J, U, J

VALID:   U, U, J, U, J, U, U, J, U, J


Part 3:  Modify your solution in part 2 that returns the optimal seating 
for three countries, US, Japan, and Korea (U, J, K)

Example: 8 USA representatives, 6 K, 4 Japan representatives. 

Answer: U, U, J, U, U, J, U, U, J, U, U, J

*/


fun main() {
    val us = 6
    val japan = 4

    val a= distribute(us,japan)
    println(a.joinToString())


}

fun distribute(us: Int, japan: Int): List<String> {

    val reps = mutableListOf<String>()
    val total = us + japan
    if(us % japan == 0) {

        val ratio = us/japan

        for(i in 1..japan) {
            repeat(ratio) {
                reps.add("U")
            }
            reps.add("J")
        }

        return reps
    } else {
        val ratio = us.toDouble()/japan.toDouble()
        for(i in 1..japan) {
            if(i % 2 !=0 ) {
                repeat(ceil(ratio).toInt()) {
                    reps.add("U")
                }
                reps.add("J")
            } else {
                repeat(ratio.toInt()) {
                    reps.add("U")
                }
                reps.add("J")
            }
        }
        return reps
    }


}