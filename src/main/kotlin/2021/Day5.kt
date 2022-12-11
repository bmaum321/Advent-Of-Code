package `2021`

import java.io.File

fun main() {
    data class Coordinate(val start: Pair<Int, Int>, val end: Pair<Int, Int>)

    val coordinates = mutableListOf<Coordinate>()

    val input = File("src/`2021`.main/kotlin/inputs/day5.txt").readLines().map {
        it.replace(" -> ", ",")
    }.map { it ->
        it
            .split(",")
            .map { it.toInt() }
    }

    input.forEach { numList ->
        coordinates.add(Coordinate(start = Pair(numList[0], numList[1]), end = Pair(numList[2], numList[3])))
    }

    // if x1 != x2 || y1 != y2 ignore the line
    val straightCoordinates = coordinates.filter { it.start.first == it.end.first || it.start.second == it.end.second }
    val diagonalCoordinates = coordinates.filterNot { it.start.first == it.end.first || it.start.second == it.end.second }

    val grid = List(1000) { MutableList(1000) { 0 } }
    //val grid =  List(10){MutableList(10){0}}



  // plot straight coordinates
    straightCoordinates.forEach { c ->
        if (c.start.first == c.end.first) {
            if (c.start.second < c.end.second) {
                for (i in c.start.second..c.end.second) {
                    grid[c.start.first][i] ++
                }
            } else {
                for (i in c.start.second downTo c.end.second) {
                    grid[c.start.first][i] ++
                }
            }

        } else {
            if (c.start.first < c.end.first) {
                for (i in c.start.first..c.end.first) {
                    grid[i][c.start.second] ++
                }
            } else {
                for (i in c.start.first downTo c.end.first) {
                    grid[i][c.start.second] ++
                }
            }
        }
    }

    // plot diagonal coordinates
    // 4 scenarios, (+,-), (-, +), (+,+), (-,-)
    diagonalCoordinates.forEach { c ->
       if(c.start.first < c.end.first) {
           if (c.start.second < c.end.second) { //(+,+)
               var j = c.start.second
               for(i in c.start.first..c.end.first) {
                   grid[i][j] ++
                   j++
               }
           } else { //(+,-)
               var j = c.start.second
               for(i in c.start.first..c.end.first) {
                   grid[i][j] ++
                   j--
               }
           }
       } else {
           if (c.start.second < c.end.second) { //(-,+)
               var j = c.start.second
               for(i in c.start.first downTo c.end.first) {
                   grid[i][j] ++
                   j++
               }
           } else { //(-,-)
               var j = c.start.second
               for(i in c.start.first downTo c.end.first) {
                   grid[i][j] ++
                   j--
               }
           }
       }
    }


    // Count all grid coordinates greater than or equal to 2
    val count = grid.flatten().count { it >= 2 }

    println("Answer: $count") //19349 is right answer for straight and diag

}