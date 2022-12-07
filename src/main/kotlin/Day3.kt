class Day3 {
    fun part1(input: List<String>): Int {

        val columns = input.first().indices
        val gammaRate = buildString {
            for (column in columns) {
                /**
                 * This is the same as val bitcount = input.countBitsInColumn(column)
                 * val zeroes = bitcount.zeroes
                 * val ones = bitcount.ones
                 */
                val (zeroes, ones) = input.countBitsInColumn(column)
                val commonBit = if (zeroes > ones) "0" else "1"
                append(commonBit)
            }
        }
        val epsilonRate = gammaRate.invertBinaryString()
        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }

    private fun String.invertBinaryString() = this
        .asIterable()
        .joinToString("") { if (it == '0') "1" else "0" }

    private fun List<String>.countBitsInColumn(column: Int): BitCount {
        var zeroes = 0
        var ones = 0
        for (line in this) {
            if (line[column] == '0') zeroes++ else ones++
        }

        return BitCount(zeroes, ones)

    }

    fun part2(input: List<String>): Int {
        fun oxyRating(): String {
            var candidates = input
            val columns = input.first().indices
            for (column in columns) {
                val (zeroes, ones) = candidates.countBitsInColumn(column)
                val commonBit = if (zeroes > ones) '0' else '1'

                candidates = candidates.filter { it[column] == commonBit }
                if (candidates.size == 1) break
            }
            return candidates.single()
        }

        fun c02Rating(): String {
            var candidates = input
            val columns = input.first().indices
            for (column in columns) {
                val (zeroes, ones) = candidates.countBitsInColumn(column)
                val commonBit = if (zeroes > ones) '0' else '1'

                candidates = candidates.filter { it[column] != commonBit }
                if (candidates.size == 1) break
            }
            return candidates.single()
        }
        return oxyRating().toInt(2) * c02Rating().toInt(2)
    }




    data class BitCount(val zeroes: Int, val ones: Int)
}