package `2020`

import java.io.File

fun main() {
    val input = File("src/main/kotlin/inputs/2020/day3.txt").readLines()
    val xLength = input.first().length

    val trees = findTrees(input, xLength, 1, 1) *
        findTrees(input, xLength, 3, 1) *
        findTrees(input, xLength, 5, 1) *
        findTrees(input, xLength, 7, 1) *
        findTrees(input, xLength, 1, 2)

    val vectors = listOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
    val trees2 = vectors.map { solve(input, it).toLong() }.reduce { a,b -> a * b  }



    println(trees)
    println(trees2)
}

fun findTrees(
    input: List<String>,
    xLength: Int,
    right: Int,
    down: Int
): Long {
    var coordinate = Pair(down,right)
    var trees = 0
    while (coordinate.first <= input.lastIndex) {

        if (coordinate.second >= xLength) {
            coordinate = Pair(coordinate.first, coordinate.second - xLength)
        }
        if (input[coordinate.first][coordinate.second] == '#') {
            trees++
        }
        coordinate = Pair(coordinate.first + down, coordinate.second + right)
    }
    return trees.toLong()
}

private fun solve(input: List<String>, vector: Pair<Int, Int>): Int {
    val (dx, dy) = vector
    val width = input.first().length
    return input.indices.count { y ->
        y % dy == 0 && input[y][y / dy * dx % width] == '#'
    }
}


