package `2022`

import java.io.File

fun main() {
    val grid = File("src/main/kotlin/inputs/2022/day8.txt")
        .readText()
        .split("\n")
        .map { it.trim().toList() }
        .map { array ->
            array.map { it.digitToInt() }
        }

     val gridHeight: Int = grid.size
     val gridWidth: Int = grid.first().size

    fun List<List<Int>>.viewFrom(x: Int, y: Int): List<List<Int>> =
        listOf(
            (y - 1 downTo 0).map { this[it][x] }, // Up
            (y + 1 until gridHeight).map { this[it][x] }, // Down
            this[y].take(x).asReversed(), // Left
            this[y].drop(x + 1) // Right
        )

    fun List<List<Int>>.isVisible(x: Int, y: Int): Boolean =
        viewFrom(x, y).any { direction ->
            direction.all { it < this[y][x] }
        }

    fun solvePart1(): Int = (1 until gridHeight - 1).sumOf { y ->
        (1 until gridWidth - 1).count { x ->
            grid.isVisible(x, y)
        }
    } + (2 * gridHeight) + (2 * gridWidth) - 4 // adding border trees

    val answer = solvePart1()
    println(answer)

    val x = grid.viewFrom(2,3)
    x.forEach(::println)


}

