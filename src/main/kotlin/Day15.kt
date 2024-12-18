
import Day15.Movement.DOWN
import Day15.Movement.LEFT
import Day15.Movement.RIGHT
import Day15.Movement.UP

object Day15 {

    fun sumOfGpsCoordinates(input: Input, factor: Int): Int =
        parse(input, factor).let { (warehouse, movements) ->
            movements.attempt(warehouse).also { it.printWarehouse() }.boxesGps().sum()
        }

    private data class Box(val position: Position, val width: Int) {
        operator fun contains(position: Position): Boolean =
            position.y == this.position.y && position.x in this.position.x until (this.position.x + this.width)

        fun after(movement: Movement): List<Position> =
            positions().map { it.after(movement) }

        private fun positions(): List<Position> =
            (0 until this.width).map { this.position.x + it to this.position.y }
    }

    private operator fun Set<Box>.contains(position: Position): Boolean =
        this.any { position in it }

    private data class Warehouse(
        val walls: Set<Position>,
        val boxes: Set<Box>,
        val robot: Position,
        val factor: Int
    ) {

        fun boxesGps(): List<Int> =
            boxes.map { it.position.x + 100 * it.position.y }

        fun moveRobot(movement: Movement): Warehouse =
            robot.after(movement).let { nextPos ->
                if (nextPos in walls) this
                else if (nextPos in boxes) boxesAffectedByPush(listOf(nextPos), movement, emptySet()).let { affected ->
                    if (affected.isSpaceBehind(movement)) shiftBoxes(affected, movement).withRobot(nextPos)
                    else this
                }
                else this.withRobot(nextPos)
            }//.also { printWarehouse() }

        private fun Set<Box>.shifted(movement: Movement): Set<Box> =
            this.map { Box(it.position.after(movement), it.width) }.toSet()

        private fun shiftBoxes(affected: Set<Box>, movement: Movement): Warehouse =
            Warehouse(walls, boxes - affected + affected.shifted(movement), robot, factor)

        private fun Set<Box>.isSpaceBehind(movement: Movement): Boolean =
            this.flatMap { it.after(movement) }.all { it in this || it !in walls }

        private fun boxesAffectedByPush(target: List<Position>, movement: Movement, affected: Set<Box>): Set<Box> =
            if (target.isEmpty()) affected
            else target
                .filter { it !in affected }
                .mapNotNull { position -> boxes.find { position in it } }
                .let { found -> boxesAffectedByPush(found.flatMap { it.after(movement) }, movement, affected + found) }

        fun printWarehouse() {
            walls.maxOf { it.x }.let { maxX ->
                walls.maxOf { it.y }.let { maxY ->
                    (0..maxY).joinToString("\n") { y ->
                        (0..maxX).joinToString("") { x ->
                            when {
                                x to y in boxes -> when {
                                    factor == 1 -> "O"
                                    boxes.map { it.position }.any { it == x to y } -> "["
                                    else -> "]"
                                }

                                x to y in walls -> "#"
                                x to y == robot -> "@"
                                else -> "."
                            }
                        }
                    }
                }.let { println(it) }
            }
        }

        fun withWall(wall: Position): Warehouse = Warehouse(walls + wall.byFactor(factor), boxes, robot, factor)
        fun withBox(box: Position): Warehouse =
            Warehouse(walls, boxes + Box(box.x * factor to box.y, factor), robot, factor)

        fun withRobot(robot: Position): Warehouse = Warehouse(walls, boxes, robot, factor)

        companion object {
            fun empty(factor: Int): Warehouse =
                Warehouse(emptySet(), emptySet(), Position(-1, -1), factor)
        }
    }

    private fun Position.byFactor(factor: Int): List<Position> =
        (0 until factor).map { (x * factor + it) to y }

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

    private fun parse(input: Input, factor: Int): Pair<Warehouse, List<Movement>> =
        parseWarehouse(input.beforeBlankLine(), factor) to parseMovements(input.afterBlankLine())

    private fun parseWarehouse(input: Input, factor: Int): Warehouse =
        input.foldIndexed(Warehouse.empty(factor)) { y, warehouse, line ->
            line.foldIndexed(warehouse) { x, warehouse, ch ->
                when (ch) {
                    '#' -> warehouse.withWall(x to y)
                    'O' -> warehouse.withBox(x to y)
                    '@' -> warehouse.withRobot(x * factor to y)
                    else -> warehouse
                }
            }
        }//.also { it.printWarehouse() }

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
