object Day14 {

    fun safetyFactor(input: Input, areaSize: Position, seconds: Int): Int =
        parse(input, areaSize).simulate(seconds).quadrantCounts(areaSize).product()

    fun findEasterEgg(input: Input, area: Position): Int =
        parse(input, area).findEasterEgg(area, 0)

    private fun List<Robot>.findEasterEgg(areaSize: Position, secondsPassed: Int): Int =
        if (displaysEasterEgg(areaSize)) secondsPassed
            .also { println(snapshot(areaSize)) }
        else next().findEasterEgg(areaSize, secondsPassed + 1)

    private fun List<Robot>.displaysEasterEgg(areaSize: Position): Boolean =
        this.map { it.position }.let { robotPositions ->
            (0 until areaSize.y).any { y ->
                (0 until areaSize.x).map { x -> if (x to y in robotPositions) '#' else '.' }
                    .joinToString("")
                    .contains("###############################")
            }
        }

    private fun List<Robot>.snapshot(areaSize: Position): String =
        this.map { it.position }.let { robotPositions ->
            (0 until areaSize.y).map { y ->
                (0 until areaSize.x).map { x -> if (x to y in robotPositions) '#' else '.' }.joinToString("")
            }
        }.joinToString("\n")

    private fun List<Robot>.simulate(seconds: Int): List<Robot> =
        (0 until seconds).fold(this) { robots, _ -> robots.next() }

    private fun List<Robot>.next(): List<Robot> = this.map { it.next() }

    private fun List<Robot>.quadrantCounts(areaSize: Position): List<Int> =
        quadrantsOf(areaSize).map { it.count(this) }

    private fun Area.count(robots: List<Robot>): Int =
        robots.count { it.position in this }

    private operator fun Area.contains(position: Position): Boolean =
        position.x in (first.x until second.x) && (position.y in (first.y until second.y))

    private fun quadrantsOf(areaSize: Position): List<Area> =
        ((areaSize.x / 2) to (areaSize.y / 2)).let { middle ->
            listOf(
                (0 to 0) to (middle.x to middle.y),
                (middle.x + 1 to 0) to (areaSize.x to middle.y),
                (0 to middle.y + 1) to (middle.x to areaSize.y),
                (middle.x + 1 to middle.y + 1) to (areaSize.x to areaSize.y)
            )
        }

    private data class Robot(val position: Position, val velocity: Position, val area: Position) {
        fun next(): Robot = Robot((position + velocity).teleport(area), velocity, area)
    }

    private operator fun Position.plus(other: Position): Position =
        this.x + other.x to this.y + other.y

    private fun Position.teleport(area: Position): Position =
        (if (this.x < 0) this.x + area.x else this.x % area.x) to
                (if (this.y < 0) this.y + area.y else this.y % area.y)

    private fun parse(input: Input, area: Position): List<Robot> =
        input.map { it.split('=', ' ').let { it[1] to it[3] } }
            .map { parsePosition(it.first) to parsePosition(it.second) }
            .map { Robot(it.first, it.second, area) }

    private fun parsePosition(input: String): Position =
        input.split(',').let { Position(it[0].toInt(), it[1].toInt()) }

}

private typealias Area = Pair<Position, Position>
