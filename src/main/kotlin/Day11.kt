object Day11 {

    fun countStones(input: Input, blinks: Int): Long =
        parse(input).blink(blinks).count()

    private fun Stones.blink(count: Int): Stones =
        (0 until count).fold(this) { stones, _ -> stones.blink() }

    private fun Stones.blink(): Stones =
        this.flatMap {
            when {
                it.number == "0" -> listOf("1" to it.count)

                it.number.length % 2 == 0 -> listOf(
                    it.number.firstHalf() to it.count,
                    it.number.secondHalf() to it.count
                )

                else -> listOf((it.number.toLong() * 2024).toString() to it.count)
            }
        }.fold(emptyMap()) { stones, stone ->
            stones + (stone.number to stones.getOrDefault(stone.number, 0) + stone.count)
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

private typealias Stone = Map.Entry<String, Long>

private val Stone.number: String
    get() = this.key

private val Stone.count: Long
    get() = this.value

private typealias StonePair = Pair<String, Long>

private val StonePair.number: String
    get() = this.first

private val StonePair.count: Long
    get() = this.second
