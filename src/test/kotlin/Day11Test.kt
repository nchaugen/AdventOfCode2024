import Day11.countStones
import kotlin.test.Test
import kotlin.test.assertEquals

object Day11Test {

    private val sample: Input = inputFromText("""
        125 17
    """)

    private val input: Input by lazy {
        inputFromFile("day11-input.txt")
    }

    @Test
    fun shouldStonesAfter25Blinks() {
        assertEquals(55312L, countStones(sample, 25))
        assertEquals(207683L, countStones(input, 25))
    }

    @Test
    fun shouldStonesAfter75Blinks() {
        assertEquals(55312L, countStones(sample, 75))
        assertEquals(12530866L, countStones(input, 75)) // too low
    }

}
