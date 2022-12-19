package `2022`

import java.io.File

fun main() {

    val valueMap = mutableMapOf<Char, Int>()
    var counter = 1
    for (char in 'a'..'z') {
        valueMap[char] = counter
        counter ++
    }
    for (char in 'A'..'Z') {
        valueMap[char] = counter
        counter ++
    }

    /*
    val part1 = File("src/main/kotlin/inputs/2022/day3.txt")
        .readLines()
        .asSequence()
        .map { it.chunked(it.length / 2) } // divide each string in half
        .map { list ->
            list.map {
                it.toSet() // map each string into a set
            }
        }
        .flatMap { list ->
            list.reduce { a, b -> a intersect b } // find common character in each set
        }
        .sumOf { it.toScore() }


     */

    val part1 = File("src/main/kotlin/inputs/2022/day3.txt")
        .readLines()
        .map { it.chunked(it.length/2) }
        .flatMap { (a , b) -> a.toSet() intersect b.toSet() }// The destructure declartion works here with 2 strings per list
        .sumOf { it.toScore() }
/*
    val part2 = File("src/main/kotlin/inputs/2022/day3.txt")
        .readLines()
        .asSequence()
        .map { it.toSet() }
        .chunked(3)// groups of 3
        .flatMap { list->
            list.reduce { a, b -> a intersect b } // find common character in each set
        }
        .sumOf { valueMap[it]!! }

 */

    val part2 = File("src/main/kotlin/inputs/2022/day3.txt")
        .readLines()
        .chunked(3) {
            val (a,b,c) = it
            val common = a intersect b intersect c
            common.single()
        }
        .sumOf { it.toScore() }


    println(part1)

    println(part2)

    //input.forEach(::println)
}

private fun Char.toScore(): Int =
    if (this.isUpperCase()) {
        this - 'A' + 27
    } else {
        this - 'a' + 1
    }

infix fun String.intersect(other: String) = toSet() intersect other.toSet()
infix fun Set<Char>.intersect(other: String) = this intersect other.toSet()