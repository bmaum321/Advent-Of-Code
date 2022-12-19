package `2020`

import java.io.File

fun main() {
    val nl = System.lineSeparator()
    val groups = File("src/main/kotlin/inputs/2020/day6.txt")
        .readText()
        .trim()
        .split("$nl$nl")


    val answer = groups.sumOf { it.replace(nl,"").toSet().count() } // I originally had .toCharArray().distinct() toSet removes a function call

    println(answer)

    var totalCommonAnswers = 0

    /**
     * My solution
     */
    groups.forEach { group ->
        val numberOfLinesInGroup = group.split(nl).size
        val commonAnswersInGroup = group
            .split(nl)
            .map { it.toList() }
            .flatten()
            .groupingBy { it }
            .eachCount()
            .filterValues { it == numberOfLinesInGroup }
            .count()
        totalCommonAnswers += commonAnswersInGroup
    }
    println(totalCommonAnswers)


    val secondAnswer2 = groups.map { group ->
        group.split(nl).map(String::toSet)}
    /**
     * Jetbrains solution
     */
    val secondAnswer = groups.map { group ->
        group.split(nl).map { it.toSet() }
    }.sumOf { answerSets ->
        answerSets.reduce { a, b -> a intersect b  }.count()
    }

    println(secondAnswer)
}