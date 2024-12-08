import Day7.calculateTotalWithThreeOperators
import Day7.calculateTotalWithTwoOperators
import kotlin.test.Test
import kotlin.test.assertEquals

object Day7Test {

    private val sample: Input = inputFromText("""
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """)

    private val input: Input by lazy {
        inputFromFile("day7-input.txt")
    }

    @Test
    fun shouldCalculateTotalWithTwoOperators() {
        assertEquals(3749, calculateTotalWithTwoOperators(sample))
        assertEquals(12553187650171, calculateTotalWithTwoOperators(input))
    }

    @Test
    fun shouldCalculateTotalWithThreeOperators() {
        assertEquals(11387, calculateTotalWithThreeOperators(sample))
        assertEquals(96779702119491, calculateTotalWithThreeOperators(input))
    }

}
