fun main() {
    val data = listOf(0,1,1,1,1,1,0,1,1,1,1)
    val sub = data.subList(4, data.lastIndex + 1)

    sub.forEach(::println)
}