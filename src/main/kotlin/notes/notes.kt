package notes

fun main() {
    val nums = listOf(1,2,4)
    val target = 6
    val answer = mutableListOf<Int>()

    for(i in nums.indices) {
        for(j in i+1 until nums.size) {
            if(nums[i] + nums[j] == target) {
                answer.addAll(listOf(nums[i],nums[j]))
                break
            }
        }
    }

    answer.forEach(::println)


    val complements = nums.associateBy { target - it }.toMutableMap()
    if(target/2 in complements.keys && ((nums.count { it == target / 2}) < 2)) {
        complements.remove(3)
    }
    complements.forEach(::println)
    val answer2 = nums.firstNotNullOfOrNull { number ->
        complements[number]?.let { complement ->
            Pair(complement, number)
        }


    }

    println(answer2)

}