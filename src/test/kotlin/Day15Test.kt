import kotlin.test.Test
import kotlin.test.assertEquals

object Day15Test {

    private val sample: Input = inputFromText("""
        ##########
        #..O..O.O#
        #......O.#
        #.OO..O.O#
        #..O@..O.#
        #O#..O...#
        #O..O..O.#
        #.OO.O.OO#
        #....O...#
        ##########
        
        <vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^
        vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v
        ><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<
        <<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^
        ^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><
        ^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^
        >^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^
        <><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>
        ^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>
        v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^
    """)

    private val sample2: Input = inputFromText("""
        #######
        #.....#
        #.....#
        #.@O..#
        #..#O.#
        #...O.#
        #..O..#
        #.....#
        #######
        
        >><vvv>v>^^^
    """)

    private val input: Input by lazy {
        inputFromFile("day15-input.txt")
    }

    @Test
    fun shouldCalculateGpsCoordinates() {
        assertEquals(10092, Day15.sumOfGpsCoordinates(sample, 1))
        assertEquals(1456590, Day15.sumOfGpsCoordinates(input, 1))
    }

    @Test
    fun shouldCalculateGpsCoordinatesInSecondWarehouse() {
        assertEquals(9021, Day15.sumOfGpsCoordinates(sample, 2))
        assertEquals(1430, Day15.sumOfGpsCoordinates(sample2, 2))
        assertEquals(1489116, Day15.sumOfGpsCoordinates(input, 2)) //too low
    }

}
