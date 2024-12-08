import Day8.countAntinodeLocations
import Day8.countResonantAntinodeLocations
import kotlin.test.Test
import kotlin.test.assertEquals

object Day8Test {

    private val sample: Input = inputFromText("""
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............
    """)

    private val input: Input by lazy {
        inputFromFile("day8-input.txt")
    }

    @Test
    fun shouldCountAntinodeLocations() {
        assertEquals(14, countAntinodeLocations(sample))
        assertEquals(244, countAntinodeLocations(input))
    }

    @Test
    fun shouldCountAntinodeLocationsWithResonantHarmonics() {
        assertEquals(34, countResonantAntinodeLocations(sample))
        assertEquals(912, countResonantAntinodeLocations(input))
    }

}
