package `2022`

import java.io.File

fun main() {
    val nl = System.lineSeparator()
    val part1 = mutableListOf<Int>()
    val part2 = mutableListOf<Int>()
    val input = File("src/main/kotlin/inputs/2022/day2.txt")
        .readLines()
        .map { line ->
            val (theirChoice, myChoice) = line.split(" ")
            when (theirChoice) {
                "A" -> { //Rock
                    when (myChoice) {
                        "X" -> {
                            part1.add(4) // 1 + 3 (draw)
                            part2.add(3)// scissors (loss)
                        }
                        "Y" -> {
                            part1.add(8)// 2 + 6 (win)
                            part2.add(4)// 1 + 3 (draw with rock)
                        }
                        "Z" -> {
                            part1.add(3)// 3 + 0 (loss)
                            part2.add(8)// 6 + 2 (win with paper)
                        }

                        else -> {}
                    }
                }

                "B" -> { //Paper
                    when (myChoice) {
                        "X" -> {
                            part1.add(1)// 1 + 0 (loss)
                            part2.add(1)// lose with rock
                        }
                        "Y" -> {
                            part1.add(5)// 2 + 3 (draw)
                            part2.add(5) // 3 + 2 (draw with paper)
                        }
                        "Z" -> {
                            part1.add(9)// 3 + 6 (win)
                            part2.add(9) // 6 + 3 win with scissors
                        }

                        else -> {}
                    }
                }

                "C" -> { //Scissors
                    when (myChoice) {
                        "X" -> {
                            part1.add(7)// 1 + 6 (win)
                            part2.add(2) // lose with paper
                        }
                        "Y" -> {
                            part1.add(2)// 2 + 0 (loss)
                            part2.add(6) // 3 + 3 draw with scissors
                        }
                        "Z" -> {
                            part1.add(6)// 3 + 3 (draw)
                            part2.add(7)// 6 + 1 win with rock
                        }

                        else -> {}
                    }
                }

                else -> {}
            }
        }

    println(part1.sum())
    println(part2.sum())
}