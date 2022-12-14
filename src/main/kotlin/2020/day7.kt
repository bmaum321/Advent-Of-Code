package `2020`

import java.io.File

typealias Color = String
typealias Rule = Set<String>

private const val SHINY_GOLD = "shiny gold"

fun main() {
    val rules: Map<Color, Rule> = buildBagTree()
    val containers = findContainersDFS(rules)
    println(containers)
    println()
    println(containers.size)

}

fun findContainersDFS(rules: Map<Color, Set<String>>): Set<Color> {
    var known = setOf(SHINY_GOLD)
    var next = setOf(SHINY_GOLD) + rules[SHINY_GOLD]!!
    while(true) {
        val toFind = next - known
        if(toFind.isEmpty()) break
        known = known + next
        next = toFind.mapNotNull { rules[it] }.flatten().toSet()
    }

    return known - SHINY_GOLD
}

fun buildBagTree(): Map<Color, Set<String>> {
    val rules = hashMapOf<Color, Rule>()
    File("src/main/kotlin/inputs/2020/day7.txt")
        .forEachLine { line ->
            val (parent, allChildren) = line
                .replace(Regex("\\d+"), "") // remove all digits
                .replace(Regex("bags?\\.?"), "") //remove word bags or bag.
                .split("contain")
                .map { it.trim() }
            val childrenColors = allChildren.split(",").map { it.trim() }.toSet()
            for(childColor in childrenColors) {
                rules.compute(childColor){ _, current ->
                    if (current==null) setOf(parent)
                    else current + parent
                }
            }
        }
    return rules

}
