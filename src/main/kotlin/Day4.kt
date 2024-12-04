import kotlin.collections.getOrElse

object Day4 {
    fun countWord(input: List<String>, word: String): Int =
        (input + horizontal(input) + diagonal(input) + reverseDiagonal(input))
            .sumOf { it.countBothWays(word) }

    fun horizontal(input: List<String>): List<String> =
        input.fold(emptyList()) { acc: List<String>, line ->
            line.mapIndexed { i, ch -> acc.getOrElse(i) { _ -> "" } + ch }.toList()
        }

    fun diagonal(input: List<String>): List<String> =
        horizontal(input.mapIndexed { index, line -> ".".repeat(index) + line + ".".repeat(line.count() - index - 1) })

    fun reverseDiagonal(input: List<String>): List<String> =
        horizontal(input.mapIndexed { index, line -> ".".repeat(line.count() - index - 1) + line + ".".repeat(index) })

    private fun String.countBothWays(word: String): Int = this.count(word) + this.count(word.reversed())
    private fun String.count(word: String): Int = this.split(word).count() - 1

    fun countXmas(input: List<String>): Int =
        input.first().drop(2).indices.let { xRange ->
            input.windowed(3).sumOf { xRange.count { x -> it.containsXmasAt(x) } }
        }

    private fun List<String>.containsXmasAt(x: Int): Boolean =
        this.joinToString("") { it.substring(x..x + 2) }
            .filterIndexed { index, _ -> index % 2 == 0 }
            .let { listOf("MMASS", "MSAMS", "SSAMM", "SMASM").contains(it) }

}
