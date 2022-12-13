

import java.io.File

fun main() {


    val entryList = mutableListOf<Entry>()

    val passwords = File("src/main/kotlin/inputs/2020/day2.txt")
        .readLines()
        .map { Entry.parse(it) }

    passwords.forEach(::println)

    val validPasswords1 = passwords.count { it.partOne() }
    val validPasswords2 = passwords.count { it.partTwo() }
    /*
    passwords.forEach { entry ->
        var occurence = 0
        for(char in entry.password) {
            if(char == entry.char) occurence++
        }
        if(occurence in entry.range) validPasswords ++
    }

     */

    println(validPasswords1)
    println(validPasswords2)

}

data class Entry(
    val range: IntRange,
    val char: Char,
    val password: String
) {

    fun partOne() = password.count { it == char } in range

    fun partTwo() = (password[range.first-1] == char) xor (password[range.last-1] == char)
   // fun partTwo() = ((password[range.first-1] == char && password[range.last-1] != char) || (password[range.first-1] != char && password[range.last-1] == char))
    companion object{
        fun parse(line: String) = Entry(
            password = line.substringAfter(": "),
            char = line.substringAfter(" ").substringBefore(":").single() ,
            range = IntRange(line.substringBefore(" ").split("-").first().toInt(), line.substringBefore(" ").split("-").last().toInt())
        )

        }
    }




