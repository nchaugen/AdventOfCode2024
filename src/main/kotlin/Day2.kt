import kotlin.collections.all
import kotlin.collections.slice
import kotlin.math.abs

object Day2 {
    fun countSafeReports(input: List<String>): Int =
        parse(input).count { it.isSafe() }

    fun countSafeReportsWithDampener(input: List<String>): Int =
        parse(input).count { it.isSafeWithDampener() }

    fun parse(input: List<String>): List<List<Int>> =
        input.map { it.split(whitespace).map { it.toInt() } }

    private val whitespace = Regex("""\s+""")

    private fun List<Int>.isSafe(): Boolean =
        this.zipWithNext { a, b -> a - b }
            .let { diffs -> diffs.all { abs(it) < 4 } && (diffs.all { it < 0 } || diffs.all { it > 0 }) }

    private fun List<Int>.isSafeWithDampener(): Boolean =
        this.indices.any { slice((0..<size) - it).isSafe() }
}
