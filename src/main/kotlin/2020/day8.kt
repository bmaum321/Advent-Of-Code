package `2020`

import java.io.File

fun main() {
    val instructions = File("src/main/kotlin/inputs/2020/day8.txt")
        .readLines()
        .map { it.toInstruction() }


    var acc = 0
    var idx = 0
    for (instruction in instructions) {
        if(instructions[idx].executed) break
        when (instructions[idx].action) {
            "acc" -> {
                acc += instructions[idx].amount
                instructions[idx].executed = true
                idx ++
            }
            "jmp" -> {
                instructions[idx].executed = true
                idx += instructions[idx].amount
            }
            else -> {
                instructions[idx].executed = true
                idx ++
            }
        }
    }

    println(acc)

}

data class Instruction2(
        val action: String,
        val amount: Int,
        var executed: Boolean = false
)

fun String.toInstruction(): Instruction2 {
    return Instruction2(
            action = this.take(3),
            amount = this.substringAfter(" ").toInt()
    )
}