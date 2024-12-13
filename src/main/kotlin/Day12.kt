import kotlin.collections.count
import kotlin.collections.find

object Day12 {

    fun calculateFencingPriceByPerimeter(input: Input): Int =
        parse(input).let { regions ->
            regions.sumOf { it.perimeterFencingPrice() }
        }

    fun calculateFencingPriceBySides(input: Input): Int =
        parse(input).let { regions ->
            regions.sumOf {
                it.sideFencingPrice()
            }
        }

    private fun Region.perimeterFencingPrice(): Int =
        this.count() * this.sumOf { it.countFencedPerimeter() }

    private fun Region.sideFencingPrice(): Int =
        this.count() * this.sumOf {
            it.countFencedSide(this)
        }

    private fun FencedPlot.countFencedPerimeter(): Int =
        this.second.toString(2).count { it == '1' }

    private fun FencedPlot.countFencedSide(region: Region): Int =
        (if (!this.hasNorthFence() || (region.plotWestOf(this)?.hasNorthFence() == true)) 0 else 1) +
                (if (!this.hasSouthFence() || (region.plotWestOf(this)?.hasSouthFence() == true)) 0 else 1) +
                (if (!this.hasEastFence() || (region.plotNorthOf(this)?.hasEastFence() == true)) 0 else 1) +
                (if (!this.hasWestFence() || (region.plotNorthOf(this)?.hasWestFence() == true)) 0 else 1)

    private fun FencedPlot.hasNorthFence(): Boolean = this.second.and(0b0001) > 0
    private fun FencedPlot.hasEastFence(): Boolean = this.second.and(0b0010) > 0
    private fun FencedPlot.hasSouthFence(): Boolean = this.second.and(0b0100) > 0
    private fun FencedPlot.hasWestFence(): Boolean = this.second.and(0b1000) > 0

    private fun Region.plotWestOf(other: FencedPlot): FencedPlot? =
        this.find { it.first.x == other.first.x - 1 && it.first.y == other.first.y }

    private fun Region.plotNorthOf(other: FencedPlot): FencedPlot? =
        this.find { it.first.x == other.first.x && it.first.y == other.first.y - 1 }

    private fun parse(input: Input): List<Region> =
        input.flatMapIndexed { y, row ->
            row.mapIndexed { x, ch ->
                Position(x, y) to ch
            }
        }.let { plots ->
            plots.fold(emptyList<Region>()) { regions, plot ->
                if (regions.any { plot.isIn(it) }) regions
                else regions + listOf(plots.regionOf(plot))
            }
        }

    private fun Plot.isIn(region: Region): Boolean =
        region.any { it.first == this.first }

    private fun List<Plot>.regionOf(plot: Plot): Region =
        this.findRegion(plot, emptyList())

    private fun List<Plot>.findRegion(plot: Plot, region: Region): Region =
        if (plot.isIn(region)) region
        else neighboursOf(plot).let { neighbours ->
            neighbours.fold(region + listOf(plot.first to (plot.fences(neighbours)))) { region, neighbour ->
                this.findRegion(neighbour, region)
            }
        }

    private fun Plot.fences(neighbours: List<Plot>): Int =
        neighbours.fold(0b1111) { count, neighbour ->
            when {
                neighbour.isNorthOf(this) -> count.xor(0b0001)
                neighbour.isEastOf(this) -> count.xor(0b0010)
                neighbour.isSouthOf(this) -> count.xor(0b0100)
                neighbour.isWestOf(this) -> count.xor(0b1000)
                else -> count
            }
        }

    private fun List<Plot>.neighboursOf(plot: Plot): List<Plot> =
        this.filter { it.first in plot.first.neighbourPositions() }.filter { it.second == plot.second }

    private fun Position.neighbourPositions(): List<Position> =
        listOf(
            Position(this.x - 1, this.y),
            Position(this.x + 1, this.y),
            Position(this.x, this.y - 1),
            Position(this.x, this.y + 1)
        )

    private fun Plot.isNorthOf(other: Plot): Boolean = first.x == other.first.x && first.y == other.first.y - 1
    private fun Plot.isSouthOf(other: Plot): Boolean = first.x == other.first.x && first.y == other.first.y + 1
    private fun Plot.isWestOf(other: Plot): Boolean = first.x == other.first.x - 1 && first.y == other.first.y
    private fun Plot.isEastOf(other: Plot): Boolean = first.x == other.first.x + 1 && first.y == other.first.y
}

private typealias Region = List<FencedPlot>
private typealias Plot = Pair<Position, Char>
private typealias FencedPlot = Pair<Position, Int>
