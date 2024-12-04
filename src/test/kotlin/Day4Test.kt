import Day4.countWord
import Day4.countXmas
import Day4.diagonal
import Day4.horizontal
import Day4.reverseDiagonal
import kotlin.test.Test
import kotlin.test.assertEquals

object Day4Test {

    private val sample: List<String> = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent().split("\n")

    private val input: List<String> by lazy {
        Input.linesFrom("day4-input.txt")
    }

    @Test
    fun shouldCountWords() {
        assertEquals(18, countWord(sample, "XMAS"))
        assertEquals(2524, countWord(input, "XMAS"))
    }

    @Test
    fun shouldCountXmas() {
        assertEquals(9, countXmas(sample))
        assertEquals(1873, countXmas(input))
    }

    @Test
    fun shouldCreatedHorizontal() {
        val input: List<String> = """
            012345
            012345
            012345
            012345
            012345
            012345
        """.trimIndent().split("\n")

        val expected = """
            000000
            111111
            222222
            333333
            444444
            555555
        """.trimIndent().split("\n")

        assertEquals(expected, horizontal(input))
    }

    @Test
    fun shouldCreateDiagonal() {
        val input: List<String> = """
            012345
            123456
            234567
            345678
            456789
            567890
        """.trimIndent().split("\n")

        val expected = """
            0.....
            11....
            222...
            3333..
            44444.
            555555
            .66666
            ..7777
            ...888
            ....99
            .....0
        """.trimIndent().split("\n")

        assertEquals(expected, diagonal(input))
    }

    @Test
    fun shouldCreateReverseDiagonal() {
        val input: List<String> = """
            543210
            654321
            765432
            876543
            987654
            098765
        """.trimIndent().split("\n")

        val expected = """
            .....0
            ....99
            ...888
            ..7777
            .66666
            555555
            44444.
            3333..
            222...
            11....
            0.....
        """.trimIndent().split("\n")


        assertEquals(expected, reverseDiagonal(input))
    }


}
