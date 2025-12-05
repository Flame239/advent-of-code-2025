fun main() {
    fun isAccessible(input: List<String>, i: Int, j: Int): Boolean {
        var count = 0
        for (iDiff in -1..1) {
            for (jDiff in -1..1) {
                if (iDiff == 0 && jDiff == 0) continue
                if ('@' == input.getOrNull(i + iDiff)?.getOrNull(j + jDiff)) {
                    count++
                }
            }
        }
        return count <= 3
    }

    fun part1(input: List<String>): Int {
        var count = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '@' && isAccessible(input, i, j)) count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        // (i, j) -> count
        // find all with count <= 3, delete them, update adjacent rows
        return input.size
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}

