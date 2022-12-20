package `2022`

import java.io.File

fun main() {
    val input = File("src/main/kotlin/inputs/2022/day4.txt")
        .readLines()
        .map { ElfPair.parse(it) }

    /**
     * Find count of pairs that one range fully contain the other?
     */
    val part1 = input.count { pair ->
        pair.first.all { it in pair.second } || pair.second.all { it in pair.first }
    }

    /**
     * Find count of pairs that overlap
     */
    val part2 = input.count { pair ->
        pair.first.any { it in pair.second } || pair.second.any { it in pair.first }
    }

    println(part1)
    println(part2)
}

data class ElfPair(val first: IntRange, val second: IntRange) {
    companion object {
        fun parse(line: String): ElfPair {
            val (firstElf, secondElf) = line.split(",")
            val (firstElf1, firstElf2) = firstElf.split("-").map { it.toInt() }
            val (secondElf1, secondElf2) = secondElf.split("-").map { it.toInt() }
            return ElfPair(
                    first = IntRange(firstElf1, firstElf2),
                    second = IntRange(secondElf1, secondElf2)
            )
        }
    }
}
