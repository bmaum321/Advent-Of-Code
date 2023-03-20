import java.io.*;
import java.util.*;
import kotlin.math.*


fun main() {
    val t = listOf(listOf(2),listOf(3,4),listOf(6,5,7),listOf(4,1,8,3))
    val x = listOf(listOf(-10),listOf(10,5),listOf(1,2,3))
    val a = Solution().minimumTotal(t)
    println(a)
}

class Solution {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        if(triangle.size == 1) return triangle.first().first()
        val a = mutableListOf<Int>()
        triangle.forEach{ list ->
            if(list.size == 1 ) {
                a.add(list.first())
            } else if(list.size == 2 ) {
                a.add(minOf(list[0],list[1]))
            } else {
                val min = minOf(triangle[1][0],triangle[1][1])
                var index = triangle[1].indexOfFirst { it == min}

                for(i in 2..triangle.lastIndex) {
                    val min = minOf(triangle[i][index], triangle[i][index+1])
                    a.add(min)
                }
            }

        }

        a.forEach(::println)
        return a.sum()
    }
}

//https://leetcode.com/problems/triangle/description/