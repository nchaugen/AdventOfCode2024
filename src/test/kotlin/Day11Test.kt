import Day11.countBlinks
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
    fun shouldCountStonesAfter25Blinks() {
        assertEquals(55312L, countBlinks(sample, 25))
        assertEquals(207683L, countBlinks(input, 25))
    }

    @Test
    fun shouldCountStonesAfter75Blinks() {
        assertEquals(65601038650482L, countBlinks(sample, 75))
        assertEquals(244782991106220L, countBlinks(input, 75))
    }

}
