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
        input.dropLast(2).mapIndexed { y, line ->
            line.dropLast(2).indices.count { x -> input.containsXmasAt(y, x) }
        }.sum()

    private fun List<String>.containsXmasAt(y: Int, x: Int): Boolean =
        windowAt(y, x).let { window ->
            (diagonal(window) + reverseDiagonal(window)).sumOf { it.countBothWays("MAS") } > 1
        }

    private fun List<String>.windowAt(y: Int, x: Int): List<String> =
        this.slice(y..y + 2).map { it.substring(x..x + 2) }
}
