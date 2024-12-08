import Day6.Direction.EAST
import Day6.Direction.NORTH
import Day6.Direction.SOUTH
import Day6.Direction.WEST

object Day6 {

    fun countPositions(input: Input): Int =
        parse(input).predictGuardPositions().toSet().count() - 1

    fun countLoopPositions(input: Input): Int =
        parse(input).findLoopPositions().size

    private fun parse(input: Input): SituationMap =
        input.foldIndexed(Obstructions.NONE to Guard.NONE) { y, acc, line ->
            line.foldIndexed(acc) { x, accLine, ch ->
                when (ch) {
                    '#' -> accLine.first.with(x to y) to accLine.second
                    '^' -> accLine.first to Guard(x to y, NORTH)
                    else -> accLine
                }
            }
        }.let { (obstructions, guard) ->
            SituationMap(input.first().count() to input.size, obstructions, guard)
        }

    data class SituationMap(val size: Position, val obstructions: Obstructions, val guard: Guard) {
        private val min = -1 to -1
        private val max = size.x to size.y

        fun predictGuardPositions(): List<Position> =
            traceRoute(guard.position, predictRoute(guard, obstructions, emptyList()))

        fun findLoopPositions(): List<Position> =
            (predictGuardPositions().toSet() - guard.position)
                .filter {
                    predictRoute(guard, obstructions.with(it), emptyList())
                        .none { destination -> isOffMap(destination) }
                }

        private fun predictRoute(guard: Guard, obstructions: Obstructions, visited: List<Position>): List<Position> =
            if (isOffMap(guard.position)) visited
            else nextDestination(guard, obstructions).let { destination ->
                if (visited.contains(destination) && destination != guard.position) visited
                else predictRoute(guard.moveTo(destination), obstructions, visited + destination)
            }

        private fun traceRoute(start: Position, route: List<Position>): List<Position> =
            route.fold(listOf(start)) { acc, next -> acc + stepsBetween(acc.last(), next) }

        private fun stepsBetween(a: Position, b: Position) =
            if (a.x == b.x) progression(a, b) { it.y }.map { a.x to it }
            else progression(a, b) { it.x }.map { it to a.y }

        private fun progression(a: Position, b: Position, dimension: (Position) -> Int): IntProgression =
            if (dimension(a) <= dimension(b)) dimension(a)..dimension(b)
            else dimension(a) downTo dimension(b)

        private fun isOffMap(position: Position): Boolean =
            position.x !in 0 until size.x || position.y !in 0 until size.y

        private fun nextDestination(guard: Guard, obstructions: Obstructions): Position =
            when (guard.direction) {
                NORTH -> nextStopNorth(guard, obstructions)
                SOUTH -> nextStopSouth(guard, obstructions)
                WEST -> nextStopWest(guard, obstructions)
                EAST -> nextStopEast(guard, obstructions)
            }

        private fun nextStopNorth(guard: Guard, obstructions: Obstructions): Position =
            obstructions.nextFacingNorth(guard.position)?.let { it.x to it.y + 1 }
                ?: (guard.position.x to min.y)

        private fun nextStopSouth(guard: Guard, obstructions: Obstructions): Position =
            obstructions.nextFacingSouth(guard.position)?.let { it.x to it.y - 1 }
                ?: (guard.position.x to max.y)

        private fun nextStopWest(guard: Guard, obstructions: Obstructions): Position =
            obstructions.nextFacingWest(guard.position)?.let { it.x + 1 to it.y }
                ?: (min.x to guard.position.y)

        private fun nextStopEast(guard: Guard, obstructions: Obstructions): Position =
            obstructions.nextFacingEast(guard.position)?.let { it.x - 1 to it.y }
                ?: (max.x to guard.position.y)

    }

    data class Obstructions(val positions: List<Position>) {
        companion object {
            val NONE = Obstructions(emptyList())
        }

        fun with(position: Position): Obstructions =
            Obstructions(positions + position)

        fun nextFacingEast(from: Position): Position? =
            positions
                .filter { it.x > from.x && it.y == from.y }
                .minByOrNull { it.x }

        fun nextFacingWest(from: Position): Position? =
            positions
                .filter { it.x < from.x && it.y == from.y }
                .maxByOrNull { it.x }

        fun nextFacingSouth(from: Position): Position? =
            positions
                .filter { it.x == from.x && it.y > from.y }
                .minByOrNull { it.y }

        fun nextFacingNorth(from: Position): Position? =
            positions
                .filter { it.x == from.x && it.y < from.y }
                .maxByOrNull { it.y }

    }

    data class Guard(val position: Position, val direction: Direction) {
        companion object {
            val NONE = Guard(Position(-1, -1), NORTH)
        }

        fun moveTo(position: Position): Guard =
            Guard(position, direction.turnRight())
    }

    enum class Direction {
        NORTH, SOUTH, EAST, WEST;

        fun turnRight(): Direction =
            when (this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
    }
}
