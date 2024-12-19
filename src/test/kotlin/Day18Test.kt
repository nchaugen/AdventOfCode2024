import kotlin.test.Test
import kotlin.test.assertEquals

object Day18Test {

    private val sample: Input = inputFromText("""
        5,4
        4,2
        4,5
        3,0
        2,1
        6,3
        2,4
        1,5
        0,6
        3,3
        2,6
        5,1
        1,2
        5,5
        2,5
        6,5
        1,4
        0,4
        6,4
        1,1
        6,1
        1,0
        0,5
        1,6
        2,0
    """)

    private val input: Input by lazy {
        inputFromFile("day18-input.txt")
    }

    @Test
    fun shouldFindMinimumStepsToExit() {
        assertEquals(22, Day18.minimumSteps(sample, 12, 0 to 0, 6 to 6))
        assertEquals(250, Day18.minimumSteps(input, 1024, 0 to 0, 70 to 70))
    }

    @Test
    fun shouldFindBlockingByte() {
        assertEquals("6,1", Day18.blockingByte(sample, 12, 0 to 0, 6 to 6))
        assertEquals("56,8", Day18.blockingByte(input, 1024, 0 to 0, 70 to 70))
    }

}
