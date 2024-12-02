import Day2.countSafeReports
import Day2.countSafeReportsWithDampener
import kotlin.test.Test
import kotlin.test.assertEquals

object Day2Test {

    private val sample: List<String> = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent().split("\n")

    private val input: List<String> by lazy {
        Input.linesFrom("day2-input.txt")
    }

    @Test
    fun shouldCountSafeReports() {
        assertEquals(2, countSafeReports(sample))
        assertEquals(321, countSafeReports(input))
    }

    @Test
    fun shouldCountSafeReportsWithDampener() {
        assertEquals(4, countSafeReportsWithDampener(sample))
        assertEquals(386, countSafeReportsWithDampener(input))
    }

}
