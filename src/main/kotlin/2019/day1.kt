package `2019`

import java.io.File

fun main() {
    val input = File("src/main/kotlin/inputs/2019/day1.txt").readLines()
        .map { it.toInt() }

    val part1 = input.sumOf { (it / 3) - 2 }

    /**
     * This would be much cleaner with a recursive function
     */

    var part2 = 0
    input.forEach { num ->
        var fuel = (num/3) - 2
        var totalFuel = fuel
        while(fuel > 0) {
            fuel = (fuel/3) - 2
            if(fuel < 0) continue
            totalFuel += fuel
        }
        part2+=totalFuel
    }

    val part2optimized = input.sumOf {
        val componentFuel = (it / 3 - 2)
        componentFuel + calcMetaFuel(componentFuel)
    }

    println(part1)
    println(part2)
    println(part2optimized)


}

fun calcMetaFuel(fuel: Int): Int {
    val metaFuel = fuel / 3 - 2
    return if (metaFuel <= 0) 0 else metaFuel + calcMetaFuel(metaFuel)
}