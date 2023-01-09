package `2019`

fun main() {
    val part1 = (245318..765757).count { num ->
        num.isSorted() && num.containsPair()
    }

    println(part1)

    val part2 = (245318..765757).count { num ->
        num.isSorted() && num.containsIsolatedPair()
    }
    println(part2)

}

fun Int.isSorted(): Boolean {
    val digits = this.toString().map { it.digitToInt() }
    return digits.zipWithNext().all { it.first <= it.second }

}

fun Int.containsPair(): Boolean {
    val digits = this.toString().map { it.digitToInt() }
    return digits.zipWithNext().any { it.first == it.second }
    //return digits.groupBy { it }.any { it.value.size >= 2 }
}

fun Int.containsIsolatedPair(): Boolean {
    val digits = this.toString().map { it.digitToInt() }
    return digits.groupBy { it }.any { it.value.size == 2 }
}