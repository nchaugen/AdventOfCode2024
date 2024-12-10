import kotlin.collections.flatMap
import kotlin.collections.plus
import kotlin.collections.toSet

object Day10 {

    fun sumOfTrailheadScores(input: Input): Int =
        parse(input).let { (topoMap: TopoMap, trailheads: Set<Position>) ->
            trailheads.sumOf { topoMap.scoreOf(it) }
        }

    fun sumOfTrailheadRatings(input: Input): Int =
        parse(input).let { (topoMap: TopoMap, trailheads: Set<Position>) ->
            trailheads.sumOf { topoMap.ratingOf(it) }
        }

    private fun TopoMap.scoreOf(trailhead: Position): Int =
        findTrailsFrom(trailhead, emptyList()).map(Path::last).toSet().count()

    private fun TopoMap.ratingOf(trailhead: Position): Int =
        findTrailsFrom(trailhead, emptyList()).count()

    private fun TopoMap.findTrailsFrom(position: Position, pathToHere: Path): List<Path> =
        if (heightOf(position) == 9) listOf(pathToHere + position)
        else possibleStepsFrom(position).flatMap { findTrailsFrom(it, pathToHere + position) }

    private fun TopoMap.possibleStepsFrom(position: Position): List<Position> =
        listOf(position.up(), position.down(), position.left(), position.right())
            .filter { withinMap(it) }
            .filter { heightOf(it) == heightOf(position) + 1 }

    private fun TopoMap.heightOf(position: Position): Int =
        this[position.y][position.x]

    private fun TopoMap.withinMap(position: Position): Boolean =
        position.y >= 0 && position.y < this.size && position.x >= 0 && position.x < this.first().size

    private fun parse(input: Input): Pair<TopoMap, Set<Position>> =
        input.map { row -> row.map { ch -> ch.digitToInt() } }
            .let { topoMap ->
                topoMap to topoMap.flatMapIndexed { y, row ->
                    row.foldIndexed(emptyList<Position>()) { x, acc, height ->
                        if (height == 0) acc + Position(x, y)
                        else acc
                    }
                }.toSet()
            }
}

private typealias TopoMap = List<List<Int>>
private typealias Path = List<Position>
