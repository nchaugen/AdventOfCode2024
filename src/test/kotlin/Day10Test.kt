import Day10.sumOfTrailheadRatings
import Day10.sumOfTrailheadScores
import kotlin.test.Test
import kotlin.test.assertEquals

object Day10Test {

    private val sample: Input = inputFromText("""
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """)

    private val input: Input by lazy {
        inputFromFile("day10-input.txt")
    }

    @Test
    fun shouldSumTrailheadScores() {
        assertEquals(36, sumOfTrailheadScores(sample))
        assertEquals(574, sumOfTrailheadScores(input))
    }

    @Test
    fun shouldSumTrailheadRatings() {
        assertEquals(81, sumOfTrailheadRatings(sample))
        assertEquals(1238, sumOfTrailheadRatings(input))
    }

}
