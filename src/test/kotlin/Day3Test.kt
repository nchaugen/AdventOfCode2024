import Day3.sumOfEnabledMul
import Day3.sumOfMul
import kotlin.test.Test
import kotlin.test.assertEquals

object Day3Test {

    private val sample: List<String> = Input.fromText("""
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """)

    private val input: List<String> by lazy {
        Input.fromFile("day3-input.txt")
    }

    @Test
    fun shouldSumMulInstructions() {
        assertEquals(161, sumOfMul(sample))
        assertEquals(170807108, sumOfMul(input))
    }

    @Test
    fun shouldSumMulInstructionsConditionally() {
        assertEquals(48, sumOfEnabledMul(sample))
        assertEquals(74838033, sumOfEnabledMul(input))
    }

}
