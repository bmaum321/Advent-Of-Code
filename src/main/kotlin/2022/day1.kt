package `2022`

import java.io.File


fun main() {
    val nl = System.lineSeparator()

    val input = File("src/main/kotlin/inputs/2022/day1.txt")
        .readText()
        .split("$nl$nl")
        .map { it.split(nl) }
        .map { caloriesList ->
            caloriesList
                .map { calories ->
                    calories.toInt() } }


    val mostCalories = input.maxOf { it.sum() }

    val part2 = input
        .map { it.sum() }
        .sortedDescending()
        .take(3)
        .sum()


    println(part2)

    println(mostCalories)
}