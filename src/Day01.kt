fun main() {
    fun part1(input: List<String>): Int {
        var zeroes = 0
        var cur = 50
        input.forEach { r ->
            val direction = if (r[0] == 'R') 1 else -1
            val count = r.substring(1).toInt() * direction
            cur = Math.floorMod(cur + count, 100)
            if (cur == 0) zeroes++
        }
        return zeroes
    }

    fun part2(input: List<String>): Int {
        var zeroes = 0
        var cur = 50
        input.forEach { r ->
            val start0 = cur == 0

            val direction = if (r[0] == 'R') 1 else -1
            val count = r.substring(1).toInt() * direction

            cur += count
            if (cur < 0) {
                zeroes += (-cur / 100)
                if (!start0) zeroes++ // when 0 -> -5 we didnt cross 0
            } else if (cur > 0) {
                zeroes += (cur / 100)
            } else {
                zeroes++
            }

            cur = Math.floorMod(cur, 100)
        }
        return zeroes
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
