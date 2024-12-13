object Day13 {

    fun fewestTokensToWin(input: Input, offset: Long): Long =
        parse(input, offset).sumOf { it.findSolution()?.let { (a, b) -> 3 * a + b } ?: 0 }

    private data class Machine(val a: Delta, val b: Delta, val price: Delta) {

        // Cramer's rule
        fun findSolution(): Pair<Long, Long>? =
            (a.x * b.y - b.x * a.y).let { determinant ->
                if (determinant == 0L) null
                else ((price.x * b.y - b.x * price.y).toLong() to (a.x * price.y - price.x * a.y).toLong())
                    .let { (aNumerator, bNumerator) ->
                        if (aNumerator % determinant == 0L && bNumerator % determinant == 0L)
                            (aNumerator / determinant) to (bNumerator / determinant)
                        else null
                    }
            }

    }

    private fun parse(input: Input, offset: Long): List<Machine> =
        input.chunked(4).map { parseMachine(it, offset) }

    private fun parseMachine(input: Input, offset: Long): Machine =
        input.take(2)
            .map { it.split('+', ',').let { parts -> parts[1].toLong() to parts[3].toLong() } }
            .let { buttons ->
                input.drop(2).take(1)
                    .map { it.split('=', ',').let { parts -> parts[1].toLong() to parts[3].toLong() } }
                    .let { prize ->
                        Machine(
                            buttons.first(),
                            buttons.last(),
                            prize.first().first + offset to prize.first().second + offset
                        )
                    }
            }
}

private typealias Delta = Pair<Long, Long>

val Delta.x: Long
    get() = this.first

val Delta.y: Long
    get() = this.second
