package `2022`

import java.io.File

fun main() {
    val input = File("src/main/kotlin/inputs/2022/day5.txt")
        .readLines()
    val stacks: List<MutableList<Char>> = createStacks(input)
    val instructions: List<Instruction> = parseInstructions(input)

    fun performInstructions(reverse: Boolean) {
        instructions.forEach { (amount, source, destination) ->
            val toBeMoved = stacks[source].take(amount)
            repeat(amount) { stacks[source].removeFirst() }
            stacks[destination].addAll(0, if (reverse) toBeMoved.reversed() else toBeMoved)
        }
    }

    // true for part 1, false for part 2
    performInstructions(true)
    println(stacks.tops())
}



private fun createStacks(input: List<String>): List<MutableList<Char>> {
    val stackRows = input.takeWhile { it.contains('[') }
    return (1..stackRows.last().length step 4).map { index ->
        stackRows
            .mapNotNull { it.getOrNull(index) }
            .filter { it.isUpperCase() }
            .toMutableList()
    }
}

private fun Iterable<Iterable<Char>>.tops(): String =
    map { it.first() }.joinToString("")


private data class Instruction(val amount: Int, val source: Int, val target: Int)
private fun parseInstructions(input: List<String>): List<Instruction> =
    input
        .dropWhile { !it.startsWith("move") }
        .map { row ->
            row.split(" ").let { parts ->
                Instruction(parts[1].toInt(), parts[3].toInt() - 1, parts[5].toInt() - 1)
            }
        }