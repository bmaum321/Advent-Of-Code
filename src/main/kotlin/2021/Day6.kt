package `2021`

import java.io.File
import java.util.*

fun main(){
    val input = File("src/main/kotlin/inputs/2021/day6.txt").readText().split(",").map { it.toInt() }.toMutableList()
    val input2 = File("src/main/kotlin/inputs/2021/day6.txt").readText().split(",")

    /**
     * This approach will not work as we get to larger numbers because of Int limitations and overflows,
     * need to use a different approach
     */
    repeat(80) {
       for (i in input.indices) {
           if(input[i]-1 <0) {
               input[i] = 6
               input.add(8)
           } else {
               input[i] --
           }
       }
    }

    val answer: Long = input.size.toLong()
    println(answer)

    val part2 = part2(input2)
    println(part2)
}

private fun part2( input: List<String> ): Long
{
    val numbers = input.map { it.toInt() }

    val lanternfish = MutableList<Long>(9){0}

    numbers.groupBy { it }.mapValues { it.value.count() }.forEach {
        lanternfish[it.key] = it.value.toLong()
   }

    //This is just creating a frequency count of the numbers in the list
   // for ( number in numbers )
   // {
    //    lanternfish[number]++
   // }


    // Everything gets shifted down one, whatever was a 0 becomes an 6
    repeat(256)
    {
        Collections.rotate( lanternfish, -1 )
        lanternfish[6] += lanternfish[8]
    }

    return lanternfish.sum()
}