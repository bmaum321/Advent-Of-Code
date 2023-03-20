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


    // Get all substrings of a string --------------------------------------->
    val s = ""
    val substrings = with(s) {
        indices.asSequence().flatMap { i->
            windowedSequence(length - i)
        }
    }
    // Get all substrings of a string --------------------------------------->

    fun isPalindrome(s: String): Boolean {
        var hi = s.lastIndex
        var lo = 0

        while(lo<hi) {
            if(s[lo] != s[hi]) {
                return false
            } else {
                lo ++
                hi --
            }
        }
        return true
    }



     //Group words and sort ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    val words = listOf("i","love","leetcode","i","love","coding")
        val k = 2
        val map = words.sortedBy { it }.groupBy { it }.toList().sortedByDescending { it.second.size }
        map.forEach(::println)

    //Group words and sort ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}

/**
 * // return all combinations of a phone number ------------------------------------------->
  */

class Solution {
    private val phone = listOf("","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz")

    fun letterCombinations(digits: String): List<String>{
        val combinations = mutableListOf<String>()

        if (digits.isEmpty()) return combinations

        dfs(digits,combinations,StringBuilder(),0)
        return combinations
    }

    fun dfs(digits: String, combinationsBuilder: MutableList<String>, sb: StringBuilder, index : Int){
        if (index == digits.length){
            combinationsBuilder.add(sb.toString())
            return
        }

        val children = phone[digits[index] - '0']
        for (element in children){
            sb.append(element)
            dfs(digits,combinationsBuilder,sb,index+1)
            sb.setLength(sb.length-1)
        }
    }
}

// return all combinations of a phone number ------------------------------------------->


// Check if list of ints is soreted --------------------------------------->
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



