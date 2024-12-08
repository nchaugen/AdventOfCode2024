object Day8 {

    fun countAntinodeLocations(input: Input): Int =
        findAntinodeLocations(input, ::calculateLocation).toSet().count()

    fun countResonantAntinodeLocations(input: Input): Int =
        findAntinodeLocations(input, ::calculateResonantLocations).toSet().count()

    private fun findAntinodeLocations(
        input: Input,
        calculateLocations: (Pair<Position, Position>, Position) -> List<Position>
    ): List<Position> =
        Position(input.first().count(), input.size).let { mapSize ->
            parse(input).values
                .flatMap { findAntinodeLocations(it, mapSize, calculateLocations) }
                .also { printMap(input, it) }
        }

    private fun findAntinodeLocations(
        antennas: List<Position>,
        mapSize: Position,
        calculateLocations: (Pair<Position, Position>, Position) -> List<Position>
    ): List<Position> =
        antennas.flatMap { it -> antennas.pairAllWith(it) }
            .flatMap { calculateLocations(it, mapSize) }
            .filter { it.within(mapSize) }

    @Suppress("unused")
    private fun calculateLocation(pair: Pair<Position, Position>, mapSize: Position): List<Position> =
        listOf(calculateNextAntinode(pair))

    private fun calculateNextAntinode(pair: Pair<Position, Position>): Position =
        calculateDelta(pair).let { delta ->
            Position(pair.first.x + delta.x, pair.first.y + delta.y)
        }

    private fun calculateDelta(pair: Pair<Position, Position>): Position =
        Position(-(pair.second.x - pair.first.x), -(pair.second.y - pair.first.y))

    private fun calculateResonantLocations(pair: Pair<Position, Position>, mapSize: Position): List<Position> =
        calculateResonantIter(pair, mapSize, pair.toList())

    private fun calculateResonantIter(
        pair: Pair<Position, Position>,
        mapSize: Position,
        antinodes: List<Position>
    ): List<Position> =
        if (pair.first.within(mapSize))
            calculateNextAntinode(pair).let { next ->
                calculateResonantIter(next to pair.first, mapSize, antinodes + next)
            }
        else antinodes

    private fun List<Position>.pairAllWith(position: Position): List<Pair<Position, Position>> =
        this.map { b -> position to b }.filter { (a, b) -> a != b }

    private fun Position.within(mapSize: Position): Boolean =
        this.x in 0 until mapSize.x && this.y in 0 until mapSize.y

    private fun parse(input: Input): Map<Char, List<Position>> =
        input.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, ch -> if (ch != '.') ch to Position(x, y) else null }
        }.groupBy({ it.first }, { it.second })

    private fun printMap(map: Input, antinodes: List<Position>) {
        map.mapIndexed { y, line ->
            line.mapIndexed { x, point ->
                if (point == '.' && antinodes.anyAt(x, y)) '#' else point
            }.joinToString("")
        }.forEach(::println)
    }

    private fun List<Position>.anyAt(x: Int, y: Int): Boolean = this.any { it.x == x && it.y == y }

}
