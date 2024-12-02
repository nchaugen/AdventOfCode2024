import kotlin.collections.all
import kotlin.math.abs

object Day2 {
    fun countSafeReports(input: List<String>) =
        parse(input).count { it.isSafe() }

    fun countSafeReportsWithDampener(input: List<String>) =
        parse(input).count { it.isSafeWithDampener() }

    fun parse(input: List<String>) =
        input.map { it.split(Regex("\\s+")).map { it.toInt() } }
}

private fun List<Int>.isSafe() =
    this.zipWithNext { a, b -> a - b }
        .let { diffs -> diffs.all { abs(it) < 4 } && (diffs.all { it < 0 } || diffs.all { it > 0 }) }

private fun List<Int>.isSafeWithDampener() =
    this.indices.any { i -> (this.subList(0, i) + this.subList(i + 1, this.size)).isSafe() }
