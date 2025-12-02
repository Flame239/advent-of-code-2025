fun main() {
    fun invalidIds(upperBound: Long, maxRepeats: Int): List<Long> {
        // could be duplicates (e.g. "12" * 4 = "1212" * 2)
        val invalidIds = mutableSetOf<Long>()

        for (repeats in 2..maxRepeats) {
            var base = 1
            var cur = "1".repeat(repeats).toLong()

            while (cur < upperBound) {
                invalidIds.add(cur)
                base++
                cur = base.toString().repeat(repeats).toLong()
            }
        }

        return invalidIds.sorted()
    }

    fun part1(ranges: List<LongRange>): Long {
        var curRange = 0
        var sum = 0L
        invalidIds(ranges.last().last, 2).forEach { id ->
            while (id > ranges[curRange].last) curRange++
            if (id >= ranges[curRange].first) sum += id
        }

        return sum
    }

    fun part2(ranges: List<LongRange>): Long {
        var curRange = 0
        var sum = 0L
        val upperBound = ranges.last().last
        invalidIds(upperBound, upperBound.toString().length).forEach { id ->
            while (id > ranges[curRange].last) curRange++
            if (id >= ranges[curRange].first) sum += id
        }

        return sum
    }

    val ranges = readInput("Day02")[0]
        .split(",")
        .map { it.split("-") }
        .map { LongRange(it[0].toLong(), it[1].toLong()) }
        .sortedBy { it.first }

    part1(ranges).println()
    part2(ranges).println()
}
