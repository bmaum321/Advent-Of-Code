package `2020`

import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

data class MachineState(val instructionIndex: Int, val acc: Int)

sealed class Instruction(val action: (MachineState) -> MachineState)

fun Instruction(s: String): Instruction {
    val (instr, immediate) = s.split(" ")
    val value = immediate.toInt()
    return when (instr) {
        "nop" -> Nop(value)
        "acc" -> Acc(value)
        "jmp" -> Jmp(value)
        else -> error("Invalid opcode!")
    }
}

/**
 * Generally speaking, using immutability like this can make it much easier to reason about your system. You don’t need
 * to worry that somewhere, some code will change the internal state of the objects that you are referencing. While that
 * may not apply here, it can become a really big deal, especially if your program uses concurrency (coroutines or threads).
 * So it’s worth considering an approach like this from the get-go.
 *
However, we’re also paying a price by allocating so many objects. For a small simulation like the one we have here,
this approach is still possible, even when the task requires us to run like 600 modified programs. But if you’re
writing a simulator for a real retro gaming device, or just any machine that can store more than a few numbers,
this approach isn’t really applicable. Imagine having to copy the whole content of your machine’s RAM every time
your CPU executes an instruction – you can probably see why that wouldn’t work.
 */

class Nop(val value: Int) : Instruction(action = { MachineState(it.instructionIndex + 1, it.acc) })

class Jmp(val value: Int) : Instruction(action = { MachineState(it.instructionIndex + value, it.acc) })

class Acc(val value: Int) : Instruction(action = { MachineState(it.instructionIndex + 1, it.acc + value) })

fun execute(instructions: List<Instruction>): MachineState {
    var state = MachineState(0, 0) // set initial state to 0s
    val encounteredIndices = mutableSetOf<Int>()

    while (state.instructionIndex in instructions.indices) {
        val nextInstruction = instructions[state.instructionIndex]
        state = nextInstruction.action(state)
        if (state.instructionIndex in encounteredIndices) return state
        encounteredIndices += state.instructionIndex
    }

    println("No loop found! Program terminates")
    return state
}

fun executeMutably(instructions: List<Instruction>): MachineState {
    var ip: Int = 0
    var acc: Int = 0
    val encounteredIndices = mutableSetOf<Int>()
    while(ip in instructions.indices) {
        when(val nextInstr = instructions[ip]) {
            is Acc -> { ip++; acc += nextInstr.value }
            is Jmp -> ip += nextInstr.value
            is Nop -> ip++
        }
        if(ip in encounteredIndices) return MachineState(ip, acc)
        encounteredIndices += ip
    }
    return MachineState(ip, acc)
}

fun generateAllMutations(instructions: List<Instruction>) = sequence<List<Instruction>> {
    for ((index, instruction) in instructions.withIndex()) {
        val newProgram = instructions.toMutableList()
        newProgram[index] = when (instruction) {
            is Jmp -> Nop(instruction.value)
            is Nop -> Jmp(instruction.value)
            is Acc -> continue
        }
        yield(newProgram)
    }
}




@OptIn(ExperimentalTime::class)
fun main() {
    val instructions = File("src/main/kotlin/inputs/2020/day8.txt")
        .readLines()
        .map { Instruction(it) }
    println(measureTimedValue { execute(instructions)})
    println(measureTimedValue { executeMutably(instructions)})
    println(
            measureTimedValue {
                generateAllMutations(instructions)
                    .map { modifiedProgram -> execute(modifiedProgram) }
                    .first { state -> state.instructionIndex >= instructions.size }
            }
    )
    println(
            measureTimedValue {
                generateAllMutations(instructions)
                    .map { modifiedProgram -> executeMutably(modifiedProgram) }
                    .first { state -> state.instructionIndex >= instructions.size }
            }
    )
}