package `2020`

fun main() {
    val list = listOf(1,2,3,4,5,6,7,8,9)
    val sum = 13
    val pair = list.findPairOfSumTest(sum)
    println(pair)

    val complements = list.associateBy { sum - it }
    val pair2 = list.firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(number,complement)
        }
    }
    println(pair2)

}

fun List<Int>.findPairOfSumTest(sum: Int): Pair<Int,Int>? {
    val complements = associateBy { sum - it }
   // complements.forEach(::println)
    return firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(number, complement)
        }
    }

}


