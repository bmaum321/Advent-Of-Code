package `2020`

import java.io.File

fun main() {
    val seats = File("src/main/kotlin/inputs/2020/day5.txt").readLines()
    val seatIds = mutableListOf<Int>()

    seats.forEach { seat ->
        var row = (0..127).toList()
        var column = (0..7).toList()
        for (char in seat) {
            /**
             * The official solution converts this into a binary number
             */
            when (char) {
                'B' -> row = row.takeLast(row.size/2) //take last half
                'F' -> row = row.take(row.size/2) // take first half
                'R' -> column = column.takeLast(column.size/2)
                'L' -> column = column.take(column.size/2)
            }
        }
        seatIds.add(row.single() * 8 + column.single())
    }

    seatIds.forEach(::println)
    val maxSeat = seatIds.maxOf { it }
    println(maxSeat)
    var missingSeat = seatIds.minOf {it}
    for(seat in seatIds.minOf {it} .. seatIds.maxOf { it }) {
        missingSeat++
        if(missingSeat !in seatIds) {
            break
        }
    }

    val occupiedSeat = seatIds.toSet()
    fun isOccupied(seat: Int) = seat in occupiedSeat
    val mySeat = (1..maxSeat).find { index ->
        !isOccupied(index) && isOccupied(index - 1) && isOccupied(index + 1)
    }

    println(missingSeat)
    println(mySeat)
}