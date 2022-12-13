package `2020`

import java.io.File

fun main() {
    val passports = File("src/main/kotlin/inputs/2020/day4")
        .readText()
        .split("\n\n", "\r\n\r\n")
        .map { Passport.fromString(it) }

    val validPassports = passports.count { it.hasAllRequiredFields() }
    println(validPassports)

    val validPassports2 = passports.count { it.allFieldsValid() }
    println(validPassports2)

    val official = passports.count { it.hasValidValues() && it.hasAllRequiredFields() }
    println(official)
}

class Passport(val map: Map<String, String>) {

    companion object {
        fun fromString(s: String): Passport {
            val fieldsWithValues = s.split( " ", "\n", "\r\n")
            val map = fieldsWithValues.associate {
                val (key, value) = it.split(":")
                key to value
            }
            return Passport(map)
        }
    }

    val requiredFields = listOf(
        "pid",
        "hcl",
        "ecl",
        "hgt",
        "eyr",
        "iyr",
        "byr",
        //"cid"
    )
    fun hasAllRequiredFields() = map.keys.containsAll(requiredFields)

    fun allFieldsValid(): Boolean {
        val fields = mutableListOf<Boolean>()
        if(this.hasAllRequiredFields()) {

            // Using the !! operator on map entries here because we check if the entry has all required fields above
            val hgt = if(map["hgt"]!!.contains("cm")) map["hgt"]!!.substringBefore("cm").toInt() else
                map["hgt"]!!.substringBefore("in").toInt()
            fields.addAll(
                listOf(
                    map["pid"]!!.length == 9 && map["pid"]!!.contains("[0-9]".toRegex()),
                    map["byr"]!!.length == 4 && map["byr"]!!.toInt() in 1920..2002,
                    map["iyr"]!!.length == 4 && map["iyr"]!!.toInt() in 2010..2020,
                    map["eyr"]!!.length == 4 && map["eyr"]!!.toInt() in 2020..2030,
                    if(map["hgt"]!!.contains("cm")) hgt in 150..193 else hgt in 59..76,
                    map["hcl"]!!.first() == '#' && map["hcl"]!!.substringAfter('#').length == 6
                        && map["hcl"]!!.substringAfter('#').contains("[0-9,a-f]".toRegex()),
                    map["ecl"]!! in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                )
            )

        } else return false

        return fields.all { it }

    }

    // This is the cleaner way

    fun hasValidValues(): Boolean =
        map.all { (key, value) ->
            when(key) {
                "byr" -> value.length == 4 && value.toIntOrNull() in 1920..2022
                "iyr" -> value.length == 4 && value.toIntOrNull() in 2010..2020
                "eyr" -> value.length == 4 && value.toIntOrNull() in 2020..2030
                "pid" -> value.length == 9 && value.all { it.isDigit() }
                "ecl" -> value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") // this can be moved to constant to improve performance
                "hgt" -> when(value.takeLast(2)) {
                    "cm" -> value.removeSuffix("cm").toIntOrNull() in 150..193
                    "in" -> value.removeSuffix("in").toIntOrNull() in 59..76
                    else -> false
                }
                "hcl" -> value matches """#[0-9a-f]{6}""".toRegex()
                else -> true
            }
        }




}
