import Day13.fewestTokensToWin
import kotlin.test.Test
import kotlin.test.assertEquals

object Day13Test {

    private val sample: Input = inputFromText("""
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400
        
        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176
        
        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450
        
        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """)

    private val input: Input by lazy {
        inputFromFile("day13-input.txt")
    }

    @Test
    fun shouldFindFewestTokensToWin() {
        assertEquals(480, fewestTokensToWin(sample, 0))
        assertEquals(38714, fewestTokensToWin(input, 0))
    }

    @Test
    fun shouldFindFewestTokensToWinWithOffset() {
        assertEquals(875318608908, fewestTokensToWin(sample, 10000000000000))
        assertEquals(74015623345775, fewestTokensToWin(input, 10000000000000))
    }

}
