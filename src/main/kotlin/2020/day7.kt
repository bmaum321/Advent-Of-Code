package `2020`

import java.io.File



private const val SHINY_GOLD = "shiny gold"

fun main() {
    val rules: Map<String, Set<String>> = buildBagTree()
    val containers = findContainersDFS(rules)
    println(containers)
    println()
    println(containers.size)

    val partTwo = partTwo()
    val result = partTwo.getChildrenCountBFS(SHINY_GOLD)
    println(result)

}

val digits = "\\d+".toRegex()
private fun Map<String, Set<String>>.getChildrenCountBFS(color: String): Int {
    val children = getOrDefault(color, setOf())
    if(children.isEmpty()) return 0
    var total = 0
    for (child in children) {
        val count = digits.findAll(child).first().value.toInt()
        val bag = digits.replace(child, "").trim()
        total+=count+count*getChildrenCountBFS(bag)
    }
    return total
}

fun partTwo(): Map<String, Set<String>> {
    val rules = hashMapOf<String, Set<String>>()
    File("src/main/kotlin/inputs/2020/day7.txt")
        .forEachLine { line ->
            val (parent, allChildren) = line
                .replace(Regex("bags?\\.?"), "") //remove word bags or bag.
                .split("contain")
                .map { it.trim() }
            val rule =
                if (allChildren.contains("no other")) emptySet()
                else allChildren.split(',').map { it.trim() }.toSet()
            rules[parent] = rule
        }
    return rules

}

fun findContainersDFS(rules: Map<String, Set<String>>): Set<String> {
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

fun buildBagTree(): Map<String, Set<String>> {
    val rules = hashMapOf<String, Set<String>>()
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
