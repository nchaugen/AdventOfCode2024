import Day4.countWord
import Day4.countXmas
import Day4.diagonal
import Day4.horizontal
import Day4.reverseDiagonal
import kotlin.test.Test
import kotlin.test.assertEquals

object Day4Test {

    private val sample: List<String> = Input.fromText("""
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
    """)

    private val input: List<String> by lazy {
        Input.fromFile("day4-input.txt")
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
        val input = Input.fromText("""
            012345
            012345
            012345
            012345
            012345
            012345
        """)

        val expected = Input.fromText("""
            000000
            111111
            222222
            333333
            444444
            555555
        """)

        assertEquals(expected, horizontal(input))
    }


    @Test
    fun shouldCreateDiagonal() {
        val input = Input.fromText("""
            012345
            123456
            234567
            345678
            456789
            567890
        """)

        val expected = Input.fromText("""
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
        """)

        assertEquals(expected, diagonal(input))
    }

    @Test
    fun shouldCreateReverseDiagonal() {
        val input = Input.fromText("""
            543210
            654321
            765432
            876543
            987654
            098765
        """)

        val expected = Input.fromText("""
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
        """)

        assertEquals(expected, reverseDiagonal(input))
    }


}
