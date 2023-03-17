package `2019`
fun main() {
    val data = listOf(1 to 2, 2 to 3, 3 to 1, 4 to 6, 5 to 8)
    val maxX = data.maxOf { it.first }
    val maxY = data.maxOf { it.second}
    val graph = mutableListOf<MutableList<Char>>()
    val line = mutableListOf<Char>()
    repeat(6*(maxX + 1)){
        line.add('-')
    }

    repeat(maxY + 2){
        graph.add(line.shuffled().toMutableList())
    }

    graph.forEach { graphLine ->
        graphLine[0] = '+'
        graphLine[graphLine.lastIndex] = '+'
        graphLine.addAll(0, listOf(' ',' ',' ',' ',' ',' ',' '))
    }

    graph[maxY/2][2] = '$'

    (6 until graph[0].lastIndex step 6).forEach { index ->
        graph[0][index] = '+'
        graph[graph.lastIndex][index] = '+'
    }

    data.forEach{ point ->
        graph[graph.lastIndex - point.second][point.first * 6 + 6] = '*'
    }


    println("                        Title                  \n\n")
    graph.forEach { graphLine ->
        println(graphLine.toString()
            .replace(",","")
            .replace("[","")
            .replace("]",""))
    }
    println("\n\n                       time                 ")




}