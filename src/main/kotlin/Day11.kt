object Day11 {

    fun countBlinks(input: Input, count: Int): Long =
        parse(input).blink(count).count()

    private fun Stones.blink(count: Int): Stones =
        (0 until count).fold(this) { stones, _ -> stones.blink() }

    private fun Stones.blink(): Stones =
        this.flatMap { it ->
            when {
                it.key == "0" -> listOf("1" to it.value)
                it.key.count() % 2 == 0 -> listOf(it.key.firstHalf() to it.value, it.key.secondHalf() to it.value)
                else -> listOf((it.key.toLong() * 2024).toString() to it.value)
            }
        }.fold(emptyMap()) { stones, stone ->
            stones + (stone.first to stones.getOrDefault(stone.first, 0) + stone.second)
        }

    private fun Stones.count(): Long =
        this.keys.fold(0L) { count, stone -> count + this.getOrDefault(stone, 0) }

    private fun String.firstHalf(): String =
        this.take(this.count() / 2)

    private fun String.secondHalf(): String =
        this.drop(this.count() / 2)
            .dropWhile { it == '0' }
            .let { if (it.isEmpty()) "0" else it }

    private fun parse(input: Input): Stones =
        input.flatMap { line -> line.split(" ") }.associate { it to 1 }
}

private typealias Stones = Map<String, Long>
