fun main() {
    fun maxJoltage(input: List<Int>): Int {
        val max = input.max()
        val maxInd = input.indexOf(max)
        if (maxInd == input.lastIndex) {
            val secondMax = input.subList(0, input.lastIndex).max()
            return secondMax * 10 + max
        } else {
            val secondMax = input.subList(maxInd + 1, input.size).max()
            return max * 10 + secondMax
        }
    }

    fun toNumber(input: List<Int>): Long {
        var base = 1L
        var num = 0L
        for (i in input.indices.reversed()) {
            num += input[i] * base
            base *= 10
        }
        return num
    }

    fun maxJoltageCorrect(input: List<Int>, digits: Int): Long {
        val res = ArrayDeque<Int>()

        var toRemove = input.size - digits
        var cur = 0

        // greedy maximize digits
        while (toRemove > 0) {
            val toInd = cur + toRemove + 1
            if (toInd > input.size) return toNumber(res) // at this point we can only remove last digits

            val pref = input.subList(cur, toInd)
            val prefMax = pref.max()
            res.add(prefMax)
            val maxInd = pref.indexOf(prefMax)
            toRemove -= maxInd
            cur += (maxInd + 1)
        }

        for (i in cur..input.lastIndex) res.add(input[i])

        return toNumber(res)
    }

    fun part1(input: List<List<Int>>): Int {
        return input.sumOf { maxJoltage(it) }
    }

    fun part2(input: List<List<Int>>): Long {
        return input.sumOf { maxJoltageCorrect(it, 12) }
    }

    val input = readInput("Day03").map { l -> l.toCharArray().map { it.digitToInt() } }
    part1(input).println()
    part2(input).println()
}
