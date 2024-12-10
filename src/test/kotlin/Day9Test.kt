import Day9.compactBlocks
import Day9.compactFiles
import kotlin.test.Test
import kotlin.test.assertEquals

object Day9Test {

    private val sample: Input = inputFromText("""
        2333133121414131402
    """)

    private val input: Input by lazy {
        inputFromFile("day9-input.txt")
    }

    @Test
    fun shouldCompactBlocks() {
        assertEquals(1928, compactBlocks(sample))
        assertEquals(6359213660505, compactBlocks(input))
    }

    @Test
    fun shouldCompactFiles() {
        assertEquals(2858, compactFiles(sample))
        assertEquals(6381624803796, compactFiles(input))
    }

}
