import Day6.countLoopPositions
import Day6.countPositions
import kotlin.test.Test
import kotlin.test.assertEquals

object Day6Test {

    private val sample: Input = inputFromText("""
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """)

    private val input: Input by lazy {
        inputFromFile("day6-input.txt")
    }

    @Test
    fun shouldFindGuardPositions() {
        assertEquals(41, countPositions(sample))
        assertEquals(5312, countPositions(input))
    }

    @Test
    fun shouldFindInfiniteLoops() {
        assertEquals(6, countLoopPositions(sample))
        assertEquals(1748, countLoopPositions(input))
    }

}
