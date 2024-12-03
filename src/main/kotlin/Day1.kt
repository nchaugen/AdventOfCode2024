import kotlin.math.abs

object Day1 {
    fun totalDistance(input: List<String>): Int =
        parse(input).let { (left, right) ->
            left.sorted()
                .zip(right.sorted())
                .sumOf { (left, right) -> abs(left - right) }
        }

    fun similarityScore(input: List<String>): Int =
        parse(input).let { (left, right) ->
            left.sumOf { number -> number * right.count { it == number } }
        }

    fun parse(input: List<String>): Pair<List<Int>, List<Int>> =
        input
            .map { line -> line.split(whitespace) }
            .map { it.first().toInt() to it.last().toInt() }
            .unzip()

    private val whitespace = Regex("""\s+""")
}
