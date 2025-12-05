fun main() {
    data class Kitchen(val freshRanges: List<Segment>, val ingredients: List<Long>) {
        fun isFresh(ingredient: Long): Boolean {
            val binSearch = freshRanges.binarySearchBy(key = ingredient, selector = { it.start })
            if (binSearch >= 0) return true
            val ind = -(binSearch + 1)
            if (ind == 0) return false
            return freshRanges[ind - 1].contains(ingredient)
        }
    }

    val input = readInput("Day05")
    val ranges = input.takeWhile { it.isNotEmpty() }
        .map { it.split('-') }
        .map { Segment(it[0].toLong(), it[1].toLong()) }
    val ingredients = input.dropWhile { it.isNotEmpty() }.drop(1).map { it.toLong() }

    fun mergeRanges(ranges: List<Segment>): List<Segment> {
        val res = ArrayDeque<Segment>()

        val sorted = ranges.sortedBy { it.start }
        res.add(sorted[0])

        for (i in 1 until sorted.size) {
            val cur = sorted[i]
            val top = res.last()

            if (cur.start > top.end) {
                res.add(cur)
            } else if (cur.end > top.end) {
                top.end = cur.end
            }
        }

        return res.toList()
    }

    val k = Kitchen(mergeRanges(ranges), ingredients)

    fun part1(input: Kitchen): Int {
        return input.ingredients.count(input::isFresh)
    }

    fun part2(input: Kitchen): Long {
        return input.freshRanges.sumOf(Segment::length)
    }

    part1(k).println()
    part2(k).println()
}
