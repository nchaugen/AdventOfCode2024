object Day18 {
    fun minimumSteps(input: Input, count: Int, from: Position, to: Position): Int? =
        parse(input.take(count)).countStepsInShortestPath(from, to)

    fun blockingByte(input: Input, count: Int, from: Position, to: Position): String? =
        parse(input).findBlockingByte(from, to, count)

    private fun List<Position>.findBlockingByte(from: Position, to: Position, count: Int): String? =
        this.take(count).countStepsInShortestPath(from, to)
            ?.let { findBlockingByte(from, to, count + 1) }
            ?: this[count-1].let { (x, y) -> "$x,$y"}

    private fun List<Position>.countStepsInShortestPath(from: Position, to: Position): Int? =
        this.countSteps(setOf(from), to, 0, setOf(from))

    private fun List<Position>.countSteps(
        next: Set<Position>,
        end: Position,
        steps: Int,
        visited: Set<Position>
    ): Int? =
        if (next.isEmpty()) null
        else if (end in next) steps
        else next.fold(emptySet<Position>()) { acc, neighbour ->
            acc + neighbour.neighbours(end).filter { it !in visited }.filter { it !in this }.toSet()
        }.let { countSteps(it, end, steps + 1, visited + next) }

    private fun Position.neighbours(max: Position): List<Position> =
        listOf(this.up(), this.down(), this.left(), this.right())
            .filter { it.x in 0..max.x && it.y in 0..max.y }

    private fun parse(input: Input): List<Position> =
        input.map { it.split(',') }.map { it.first().toInt() to it.last().toInt() }

}
