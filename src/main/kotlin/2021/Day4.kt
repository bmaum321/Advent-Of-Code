package `2021`

import java.io.File

class Day4() {

    fun bingo(): Int {
        val draws = File("src/`2021`.main/kotlin/inputs/bingo.txt").readLines().first().split(",").map { it.toInt() }
        val boards: List<List<String>> = File("src/`2021`.main/kotlin/inputs/bingo.txt").readLines().drop(1).chunked(6).map { board ->
            board.filter { line ->
                line.isNotBlank()
            }
        }

        // Convert the boards into integers
        val boardInts = boards.map { board ->
            board.map { line ->
                line
                    .trim()
                    .split(Regex("""\W+""")) // Splits by either single or multiple white space
                    .map { it.toInt() }
                    .toMutableList()
            }
        }

        draws.forEach { draw ->
            boardInts.forEach { board ->
                board.forEach { line ->
                    for(i in line.indices) {
                        if(checkIfWinner(board = board)) {
                            return board.flatten().filterNot { it == 1000 }.sum() * draw
                        } else if(line[i] == draw) {
                            line[i] = 1000
                        }
                    }
                }
            }
        }
        return 0
    }

    private fun checkIfWinner(board: List<List<Int>>): Boolean {

        // Check rows
        board.forEach { line ->
            if (line.distinct().count() == 1) return true
        }

        // Check columns
        for(i in board.first().indices) {
            val column = mutableListOf<Int>()
            board.forEach { line ->
                column.add(line[i])
            }
            if(column.distinct().count() == 1) return true
        }

        return false
    }

    fun part2(): Int {
        val draws = File("src/`2021`.main/kotlin/inputs/bingo.txt").readLines().first().split(",").map { it.toInt() }
        val boards: List<List<String>> = File("src/`2021`.main/kotlin/inputs/bingo.txt").readLines().drop(1).chunked(6).map { board ->
            board.filter { line ->
                line.isNotBlank()
            }
        }

        val winnerScores = mutableListOf<Int>()

        // Convert the boards into integers
        var boardInts = boards.map { board ->
            board.map { line ->
                line
                    .trim()
                    .split(Regex("""\W+""")) // Splits by either single or multiple white space
                    .map { it.toInt() }
                    .toMutableList()
            }
        }

        draws.forEach { draw ->
            boardInts.forEach { board ->
                board.forEach { line ->
                    for(i in line.indices) {
                        if(checkIfWinner(board = board)) {
                            val score = board.flatten().filterNot { it == 1000 }.sum() * draw
                            if(score !in winnerScores) winnerScores.add(score)
                        } else if(line[i] == draw) {
                            line[i] = 1000
                        }
                    }
                }

                /**
                 * The problem here is that we keep modifying the board even after it has been marked a winner
                 */

            }
        }
        winnerScores.forEach { println(it) }
        return winnerScores.distinct().last()
    }

}