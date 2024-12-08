import java.math.BigInteger

object Day7 {

    fun calculateTotalWithTwoOperators(input: Input): Long =
        parse(input).filter { hasSolution(it, 2) }.sumOf { it.first }

    fun calculateTotalWithThreeOperators(input: Input): Long =
        parse(input).filter { hasSolution(it, 3) }.sumOf { it.first }

    private fun hasSolution(equation: Pair<Long, List<Long>>, base: Int): Boolean =
        (equation.second.size - 1).let { operatorCount ->
            (0..power(base, operatorCount))
                .any { equation.first == calculate(operatorSpec(it, base, operatorCount), equation.second) }
        }

    private fun operatorSpec(i: Int, base: Int, operatorCount: Int): String =
        i.toString(base).padStart(operatorCount, '0')

    private fun calculate(operators: String, values: List<Long>): Long =
        values.drop(1).foldIndexed(values.first()) { index, acc, value ->
            when (operators[index]) {
                '0' -> acc + value
                '1' -> acc * value
                else -> (acc.toString() + value.toString()).toLong()
            }
        }

    private fun power(base: Int, exp: Int): Int = BigInteger.valueOf(base.toLong()).pow(exp).toInt()

    fun parse(input: Input): List<Pair<Long, List<Long>>> =
        input.map { it.split(":") }
            .map { it.first().toLong() to it.last().trim().split(' ').map { it.toLong() } }
}
