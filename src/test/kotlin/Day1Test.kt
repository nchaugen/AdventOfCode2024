import Day1.similarityScore
import Day1.totalDistance
import kotlin.test.Test
import kotlin.test.assertEquals

object Day1Test {

    private val sample: Input = inputFromText("""
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """)

    private val input: Input by lazy {
        inputFromFile("day1-input.txt")
    }

    @Test
    fun shouldFindTotalDistance() {
        assertEquals(11, totalDistance(sample))
        assertEquals(2970687, totalDistance(input))
    }

    @Test
    fun shouldFindSimilarityScore() {
        assertEquals(31, similarityScore(sample))
        assertEquals(23963899, similarityScore(input))
    }

}
