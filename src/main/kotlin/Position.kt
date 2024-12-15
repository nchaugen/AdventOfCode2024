typealias Position = Pair<Int, Int>

val Position.x: Int
    get() = this.first

val Position.y: Int
    get() = this.second

fun Position.up() = Position(x, y - 1)
fun Position.down() = Position(x, y + 1)
fun Position.left() = Position(x - 1, y)
fun Position.right() = Position(x + 1, y)

/**
 * Progresses either sideways if different x values or else up-/downwards according to y values.
 * End value is not included in the progression.
 */
infix fun Position.until(end: Position): List<Position> =
    if (this.x != end.x) (this.x towards end.x).map { x -> Position(x, this.y) }
    else (this.y towards end.y).map { y -> Position(this.x, y) }

/**
 * Progresses upwards or downwards towards the end value with step 1.
 * End value is not included in the progression.
 */
infix fun Int.towards(end: Int): IntProgression =
    if (this <= end) IntProgression.fromClosedRange(this, end - 1, 1)
    else IntProgression.fromClosedRange(this, end + 1, -1)
