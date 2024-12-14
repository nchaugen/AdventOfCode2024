
import Day14.findEasterEgg
import Day14.safetyFactor
import kotlin.test.Test
import kotlin.test.assertEquals

object Day14Test {

    private val sample: Input = inputFromText("""
        p=0,4 v=3,-3
        p=6,3 v=-1,-3
        p=10,3 v=-1,2
        p=2,0 v=2,-1
        p=0,0 v=1,3
        p=3,0 v=-2,-2
        p=7,6 v=-1,-3
        p=3,0 v=-1,-2
        p=9,3 v=2,3
        p=7,3 v=-1,2
        p=2,4 v=2,-3
        p=9,5 v=-3,-3
    """)

    private val input: Input by lazy {
        inputFromFile("day14-input.txt")
    }

    @Test
    fun shouldCalculateSafetyFactor() {
        assertEquals(12, safetyFactor(sample, 11 to 7, 100))
        assertEquals(231782040, safetyFactor(input, 101 to 103, 100))
    }

    @Test
    fun shouldFindSecondsToEasterEgg() {
        assertEquals(6475, findEasterEgg(input, 101 to 103))
    }

}
