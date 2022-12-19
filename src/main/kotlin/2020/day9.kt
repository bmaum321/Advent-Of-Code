package `2020`

import java.io.File

fun main() {
    val nums = File("src/main/kotlin/inputs/2020/day9.txt")
        .readLines()
        .map { it.toLong() }

    val windows = nums.windowed(25, 1)
    var idx = 0
    var invalidNum = 0L
    for (i in 25..nums.lastIndex) {
        val pair = windows[idx].findPairOfSum(nums[i])
        if (pair == null) {
            println(nums[i])
            invalidNum = nums[i]
            break
        } else idx++
    }

    val part2list = nums.findSublistOfSum(invalidNum) ?: emptyList()
    val part2: Long? = part2list.minOrNull()?.plus(part2list.maxOrNull()!!)

    println(part2)


}


fun List<Long>.findSublistOfSum(targetSum: Long): List<Long>? =
    indices.firstNotNullOfOrNull { fromIndex ->
        ((fromIndex + 1)..size).firstNotNullOfOrNull { toIndex ->
            subList(fromIndex, toIndex).takeIf { it.sum() == targetSum }
        }
    }

fun List<Long>.findPairOfSum(sum: Long): Pair<Long, Long>? {
    val complements = associateBy { sum - it }
    return firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(number, complement)
        }
    }
}