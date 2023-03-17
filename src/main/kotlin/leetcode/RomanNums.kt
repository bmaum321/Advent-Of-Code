package leetcode

fun main() {
    val num = intToRoman(2024)
    println(num)

    val numeral = "MMXXIV"
    val int = RomanToInt(numeral)
    println(int)
}

fun intToRoman(num: Int): String {
    val ints = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    val numerals = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val sb = StringBuilder()
    var number = num
    (ints.indices).forEach {
        while(number >= ints[it]){
            sb.append(numerals[it])
            number -= ints[it]
        }
    }
    return sb.toString()
}

fun RomanToInt(numeral: String): Int {
    var answer = 0
    (0 until numeral.length - 1).forEach {
        answer += getNumber(numeral[it],numeral[it+1])
    }

    // add the last numeral
    answer += getNumber(numeral[numeral.length - 1], ' ')

    return answer
}

fun getNumber(current: Char, next: Char): Int {
    return when (current) {
        'M' -> +1000
        'D' -> +500
        'C' -> if (next == 'M' || next == 'D') -100 else 100
        'L' -> +50
        'X' -> if (next == 'C' || next == 'L') -10 else 10
        'V' -> +5
        'I' -> if (next == 'X' || next == 'V') -1 else 1
        else -> 0
    }
}