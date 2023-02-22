package `2019`

/*
 ```
 Overview:
# Write a program, in any language, that will display an ASCII chart given the following data
# data = {(1,2), (2, 3), (3, 1), (4, 6), (5, 8)}.
# You should be able to print the surrounding components of the chart and then place an * where
# each data point is specified in the data set. You do not need to print the X and Y legends
# but that would be helpful. You are given the max x and max y but if you can calculate that
# it would be helpful.
#
#  Online auction graph display
#  x axis is time
#  y axis is price
#  Title item

#  Given a two-dimension array graph the price over time
#
#     +-----+-----+-----+-----+-----+-----+
#     +                             *     +
#     +                                   +
#     +                       *           +
#   $ +                                   +
#     +                                   +
#     +           *                       +
#     +     *                             +
#     +                 *                 +
#     +-----+-----+-----+-----+-----+-----+
#                time
#
#   max x = 5
#   max y = 8
#   data = {(1,2), (2, 3), (3, 1), (4, 6), (5, 8)}
 */
fun main(){
   val data = listOf(1 to 2, 2 to 3, 3 to 1, 4 to 6, 5 to 8)
   val maxX = data.maxOf { it.first }
   val maxY = data.maxOf { it.second }

   val graph = mutableListOf<MutableList<Char>>()
   val linePiece = mutableListOf('-','-','-','-','-','-')
   val graphLine = mutableListOf<Char>()
   (1..maxX + 1).forEach { _ ->
      graphLine.addAll(linePiece)
   }

   (1..maxY + 2).forEach { _ ->
      graph.add(graphLine.shuffled().toMutableList())
   }

   graph.forEach { line ->
      line[0] = '+'
      line[line.lastIndex] = '+'
   }

   (0..graph[0].lastIndex step 6).forEach {
      graph[0][it] = '+'
      graph[graph.lastIndex][it] = '+'
   }

   data.forEach { point ->
      graph[graph.lastIndex - point.second][point.first * 6] = '*'
   }

   (1 until graph.lastIndex).forEach { y ->
      (1 until graph[0].lastIndex).forEach { x->
         if(graph[y][x] == '-') {
            graph[y][x] = ' '
         }
      }
   }

   graph.forEach { line ->
      println(line.toString().replace(",", "").replace("[","").replace("]", ""))
   }
}
