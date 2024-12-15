import Day15.Movement.DOWN
import Day15.Movement.LEFT
import Day15.Movement.RIGHT
import Day15.Movement.UP

object Day15 {

    fun sumOfGpsCoordinates(input: Input): Int =
        parse(input).let { (warehouse, movements) ->
            movements.attempt(warehouse).boxesGps().sum()
        }

    private data class Warehouse(val walls: Set<Position>, val boxes: Set<Position>, val robot: Position) {

        fun boxesGps(): List<Int> = boxes.map { it.x + 100 * it.y }

        fun moveRobot(movement: Movement): Warehouse =
            robot.after(movement).let { nextPos ->
                if (nextPos in walls) this
                else if (nextPos in boxes)
                    nextGap(movement, nextPos)
                        ?.let { nextGap -> moveBox(nextPos, nextGap).withRobot(nextPos) }
                        ?: this
                else withRobot(nextPos)
            } //.also { printWarehouse(movement) }

        private fun moveBox(from: Position, to: Position): Warehouse =
            Warehouse(walls, boxes - from + to, robot)

        private fun nextGap(movement: Movement, from: Position): Position? =
            (from until nextWall(movement, from)).firstOrNull { it !in boxes }

        private fun nextWall(movement: Movement, from: Position): Position =
            when (movement) {
                UP -> nextWallUp(from)
                DOWN -> nextWallDown(from)
                LEFT -> nextWallLeft(from)
                RIGHT -> nextWallRight(from)
            }

        private fun nextWallUp(from: Position): Position =
            walls.filter { it.x == from.x && it.y <= from.y }.maxBy { it.y }

        private fun nextWallDown(from: Position): Position =
            walls.filter { it.x == from.x && it.y >= from.y }.minBy { it.y }

        private fun nextWallLeft(from: Position): Position =
            walls.filter { it.y == from.y && it.x <= from.x }.maxBy { it.x }

        private fun nextWallRight(from: Position): Position =
            walls.filter { it.y == from.y && it.x >= from.x }.minBy { it.x }

        private fun printWarehouse(movement: Movement) {
            walls.maxOf { it.x }.let { maxX ->
                walls.maxOf { it.y }.let { maxY ->
                    (0..maxY).joinToString("\n") { y ->
                        (0..maxX).joinToString("") { x ->
                            when {
                                x to y in walls -> "#"
                                x to y in boxes -> "O"
                                x to y == robot -> "@"
                                else -> "."
                            }
                        }
                    }
                }.let { println("$it   $movement") }
            }
        }

        fun withWall(wall: Position): Warehouse = Warehouse(walls + wall, boxes, robot)
        fun withBox(box: Position): Warehouse = Warehouse(walls, boxes + box, robot)
        fun withRobot(robot: Position): Warehouse = Warehouse(walls, boxes, robot)

        companion object {
            val EMPTY = Warehouse(emptySet(), emptySet(), Position(-1, -1))
        }
    }

    private fun List<Movement>.attempt(warehouse: Warehouse): Warehouse =
        this.fold(warehouse) { warehouse, movement -> warehouse.moveRobot(movement) }

    private enum class Movement { UP, DOWN, LEFT, RIGHT }

    private fun Position.after(movement: Movement): Position =
        when (movement) {
            UP -> up()
            DOWN -> down()
            LEFT -> left()
            RIGHT -> right()
        }

    private fun parse(input: Input): Pair<Warehouse, List<Movement>> =
        parseWarehouse(input.beforeBlankLine()) to parseMovements(input.afterBlankLine())

    private fun parseWarehouse(input: Input): Warehouse =
        input.foldIndexed(Warehouse.EMPTY) { y, warehouse, line ->
            line.foldIndexed(warehouse) { x, warehouse, ch ->
                when (ch) {
                    '#' -> warehouse.withWall(x to y)
                    'O' -> warehouse.withBox(x to y)
                    '@' -> warehouse.withRobot(x to y)
                    else -> warehouse
                }
            }
        }

    private fun parseMovements(input: Input): List<Movement> =
        input.joinToString("").map {
            when (it) {
                '^' -> UP
                '>' -> RIGHT
                'v' -> DOWN
                '<' -> LEFT
                else -> throw IllegalArgumentException("Invalid movement: $it")
            }
        }

}
