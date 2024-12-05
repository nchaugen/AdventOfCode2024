import kotlin.collections.getOrElse

object Day4 {
    fun countWord(input: Input, word: String): Int =
        (input + horizontal(input) + diagonal(input) + reverseDiagonal(input))
            .sumOf { it.countBothWays(word) }

    fun horizontal(input: Input): Input =
        input.fold(emptyList()) { acc: Input, line ->
            line.mapIndexed { i, ch -> acc.getOrElse(i) { _ -> "" } + ch }.toList()
        }

    fun diagonal(input: Input): Input =
        horizontal(input.mapIndexed { index, line -> ".".repeat(index) + line + ".".repeat(line.count() - index - 1) })

    fun reverseDiagonal(input: Input): Input =
        horizontal(input.mapIndexed { index, line -> ".".repeat(line.count() - index - 1) + line + ".".repeat(index) })

    private fun String.countBothWays(word: String): Int = this.count(word) + this.count(word.reversed())
    private fun String.count(word: String): Int = this.split(word).count() - 1

    fun countXmas(input: Input): Int =
        input.first().drop(2).indices.let { xRange ->
            input.windowed(3).sumOf { xRange.count { x -> it.containsXmasAt(x) } }
        }

    private fun Input.containsXmasAt(x: Int): Boolean =
        this.joinToString("") { it.substring(x..x + 2) }
            .filterIndexed { index, _ -> index % 2 == 0 }
            .let { listOf("MMASS", "MSAMS", "SSAMM", "SMASM").contains(it) }

}
