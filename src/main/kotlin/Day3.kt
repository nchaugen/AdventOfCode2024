object Day3 {

    fun sumOfMul(input: List<String>) =
        input.flatMap {
            MUL.findAll(it).map { match ->
                match.groupValues.drop(1)
                    .map { it.toLong() }
                    .reduce { a, b -> a * b }
            }
        }.sum()

    fun sumOfEnabledMul(input: List<String>) =
        sumOfMul(
            enabledMul(emptyList(), input.joinToString())
        )

    fun enabledMul(todo: List<String>, remaining: String): List<String> =
        if (remaining.contains(DONT))
            enabledMul(
                todo + remaining.substringBefore(DONT),
                remaining.substringAfter(DONT, "").substringAfter(DO, "")
            )
        else todo + remaining

    private val MUL = Regex("mul[(](\\d+),(\\d+)[)]")
    private val DONT = "don't()"
    private val DO = "do()"

}
