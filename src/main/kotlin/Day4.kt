import java.io.File

class Day4() {

    fun bingo(): Int {
        val draws = File("src/main/kotlin/inputs/bingo.txt").readLines().first().split(",").map { it.toInt() }
        val boards: List<List<String>> = File("src/main/kotlin/inputs/bingo.txt").readLines().drop(1).chunked(6).map { board ->
            board.filter { line ->
                line.isNotBlank()
            }
        }

        val boardInts = boards.map { board ->
            board.map { line ->
                line.trim()
                    .split(Regex("\\W+"))
                    .map { it.toInt() }
            }
        }.map { board ->
            board.map { it.toMutableList() }
        }

        draws.forEach { drawedNum ->
            boardInts.forEach { board ->
                board.forEach { line ->
                    for(i in line.indices) {
                        if(line[i] == drawedNum) {
                            line[i] = 1000
                        }
                    }
                }
                if(checkIfWinner(board = board)) {
                    return board.flatten().filter { it != 1000 }.sum() * drawedNum
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

}