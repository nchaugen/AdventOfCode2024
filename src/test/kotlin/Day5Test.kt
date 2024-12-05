import Day5.sumOfCorrectRules
import Day5.sumOfIncorrectRules
import kotlin.test.Test
import kotlin.test.assertEquals

object Day5Test {

    private val sample: List<String> = Input.fromText("""
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13
        
        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """)

    private val input: List<String> by lazy {
        Input.fromFile("day5-input.txt")
    }

    @Test
    fun shouldFindCorrectUpdates() {
        assertEquals(143, sumOfCorrectRules(sample))
        assertEquals(5747, sumOfCorrectRules(input))
    }

    @Test
    fun shouldFixIncorrectUpdates() {
        assertEquals(123, sumOfIncorrectRules(sample))
        assertEquals(5502, sumOfIncorrectRules(input))
    }

}
