typealias Position = Pair<Int, Int>

val Position.x: Int
    get() = this.first

val Position.y: Int
    get() = this.second

fun Position.up() = Position(x, y - 1)
fun Position.down() = Position(x, y + 1)
fun Position.left() = Position(x - 1, y)
fun Position.right() = Position(x + 1, y)
