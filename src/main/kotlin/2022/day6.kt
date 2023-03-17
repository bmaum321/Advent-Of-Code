package `2022`

import java.io.File
import java.lang.StringBuilder

fun main() {
    val input = File("src/main/kotlin/inputs/2022/day6.txt").readText()

    val index = findDistinctLengthStartIndex(input, length = 4)
    println(index)

    val part1 = input
        .windowed(4,1)
        .indexOfFirst { it.toSet().size == 4 } + 4 // add 4 to get the start index of string
    println(part1)

    val part2 = input
        .windowed(14, 1)
        .indexOfFirst { it
            .toSet().size == 4 } + 14
    println(part2)
}

private fun findDistinctLengthStartIndex(input: String, length: Int): Int {
    val sb = StringBuilder().append(input.take(length))
    var index = length
    for (i in length..input.lastIndex) {
        if (sb.toString().toCharArray().distinct().count() == length) break
        else {
            sb.deleteCharAt(0)
            sb.append(input[i])
            index ++
        }
    }
    return index
}
