import Day1.similarityScoreFor
import Day1.totalDistanceFor
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

object Day1Test {

    private val sample: List<String> = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent().split("\n")

    private val input: List<String> by lazy {
        Input.linesFrom("day1-input.txt")
    }

    @Test
    fun shouldFindTotalDistance() {
        assertThat(totalDistanceFor(sample)).isEqualTo(11)
        assertThat(totalDistanceFor(input)).isEqualTo(2970687)
    }

    @Test
    fun shouldFindSimilarityScore() {
        assertThat(similarityScoreFor(sample)).isEqualTo(31)
        assertThat(similarityScoreFor(input)).isEqualTo(23963899)
    }

}
