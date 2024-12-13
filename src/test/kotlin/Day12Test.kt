import Day12.calculateFencingPriceByPerimeter
import Day12.calculateFencingPriceBySides
import kotlin.test.Test
import kotlin.test.assertEquals

object Day12Test {

    private val sample: Input = inputFromText("""
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE
    """)

    private val input: Input by lazy {
        inputFromFile("day12-input.txt")
    }

    @Test
    fun shouldCalculateFencingPriceByPerimeter() {
        assertEquals(1930, calculateFencingPriceByPerimeter(sample))
        assertEquals(1450422, calculateFencingPriceByPerimeter(input))
    }

    @Test
    fun shouldCalculateFencingPriceBySides() {
        assertEquals(1206, calculateFencingPriceBySides(sample))
        assertEquals(906606, calculateFencingPriceBySides(input))
    }

}
