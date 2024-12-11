object Day11 {

    fun countStones(input: Input, blinks: Int): Long =
        parse(input).let { stones ->
            (0 until blinks)
                .fold(stones) { stones, _ -> stones.blink() }
                .fold(0L) { count, _ -> count + 1 }
        }

    private fun Stones.blink(): Stones =
        this.asSequence().flatMap {
            it.count().let { digitCount ->
                when {
                    it == "0" -> listOf("1")
                    digitCount % 2 == 0 -> listOf(
                        it.take(digitCount / 2),
                        it.drop(digitCount / 2).dropWhile { it == '0' }.let { if (it.isEmpty()) "0" else it }
                    )

                    else -> listOf((it.toLong() * 2024).toString())
                }
            }
        }

    private fun parse(input: Input): Stones =
        input.flatMap { line -> line.split(" ") }.asSequence()
}

private typealias Stones = Sequence<String>
