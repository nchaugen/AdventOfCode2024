object Day3 {

    fun sumOfMul(input: Input): Long =
        input.flatMap {
            MUL.findAll(it).map { match ->
                match.groupValues.drop(1)
                    .map { it.toLong() }
                    .product()
            }
        }.sum()

    fun sumOfEnabledMul(input: Input): Long =
        sumOfMul(
            enabledMul(emptyList(), input.joinToString())
        )

    fun enabledMul(enabled: Input, remaining: String): Input =
        if (remaining.contains(DONT))
            enabledMul(
                enabled = enabled + remaining.substringBefore(DONT),
                remaining = remaining.substringAfter(DONT, "").substringAfter(DO, "")
            )
        else enabled + remaining

    private val MUL = Regex("""mul\((\d+),(\d+)\)""")
    private const val DONT = "don't()"
    private const val DO = "do()"

}
