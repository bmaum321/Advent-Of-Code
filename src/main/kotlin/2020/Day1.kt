package `2020`

import java.io.File

fun main() {
    val nums = File("src/main/kotlin/inputs/2020/day1.txt")
        .readLines()
        .map { it.toInt() }
    val sum = 2020
    val pair = nums.findPairOfSum(sum)
    println(pair)

    // Map: x-> (y,z) where y+z = 2020 - x
    val complementPairs: Map<Int, Pair<Int, Int>?> = nums.associateWith {
        nums.findPairOfSum(2020 - it)
    }


    val triple = nums.findTripleOfSum()
    println(triple)
    println(triple?.let { (x,y,z) -> (x *y*z) })


/*
    var answer = 0
    for(first in nums) {
        for(second in nums) {
            for(third in nums) {
                if (first + second + third == 2020) {
                    answer = first * second * third
                    println("$first, $second, $third")
                    println(answer)
                    return
                }
            }
        }
    }

 */



}

//This will allow for selecting the same number multiple times as well
fun List<Int>.findTripleOfSum(): Triple<Int, Int, Int>? =
    firstNotNullOfOrNull { x ->
        findPairOfSum(2020 - x)?.let { pair ->
            Triple(x, pair.first, pair.second)
        }

}

// There needs to be a check here if half the sum is in the list
fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? {
    // There needs to be a check here if half the sum is in the list
    val complements = associateBy { sum - it }.toMutableMap()
    if(sum/2 in complements.keys && this.count { it == sum / 2 } < 2){
        complements.remove(sum/2)
    }
    return firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(number, complement)
        }
    }

}