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


/**
 * Functions to sort a list of ints -------------------------------------------------->
 */
fun List<Int>.sort(): List<Int> {
    val copy = this.toMutableList()
    var i = 0
    while(!copy.isSorted()){
        if(i == copy.lastIndex){
            i = 0
        }
        if(copy[i]>copy[i+1]) {
            copy.swap(i,i+1)
            i++
        } else i++

    }

    return copy.toList()
}

fun List<Int>.isSorted(): Boolean {
    return this.zipWithNext().all { it.first <= it.second }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int){
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

/**
 * PASCALS TRIANGLE
 * ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 */


    fun generate(numRows: Int): List<List<Int>> {
        val a = mutableListOf<MutableList<Int>>()
        when (numRows) {
            0 -> return emptyList()
            1 -> return listOf(listOf(1))
            2 -> return listOf(listOf(1), listOf(1,1))
            else -> {
                a.add(mutableListOf(1))
                a.add(mutableListOf(1,1))
                for(i in 2 until numRows) {
                    val line = mutableListOf(1)
                    val pairs = a[i-1].windowed(2) // get pairs from previous line
                    pairs.forEach { line.add(it.sum()) } // add sums of pairs to new line
                    line.add(line.lastIndex + 1, 1) // add final 1 to edge
                    a.add(line)
                }

                return a
            }
        }

        return emptyList()

    }

    fun getRow(rowIndex: Int): List<Int> {

        return generate(rowIndex+1)[rowIndex]

    }


