import kotlin.collections.filter

object Day5 {

    fun sumOfCorrectRules(input: Input): Int =
        parse(input).let { (rules, updates) ->
            updates
                .filter { rules.isCorrect(it) }
                .sumOf { it[it.size / 2] }
        }

    fun sumOfIncorrectRules(input: Input): Int =
        parse(input).let { (rules, updates) ->
            updates
                .filterNot { rules.isCorrect(it) }
                .map { it.sortedWith { a, b -> rules.compare(a, b) } }
                .sumOf { it[it.size / 2] }
        }

    private fun Rules.compare(a: Int, b: Int): Int =
        if (a in this.mustBeAfter(b)) 1 else -1

    private fun Rules.isCorrect(update: List<Int>): Boolean =
        update.foldIndexed(true) { i, valid, page ->
            valid && update.slice(0..i).none { before -> before in this.mustBeAfter(page) }
        }

    private fun Rules.mustBeAfter(page: Int): List<Int> =
        this.getOrDefault(page, emptyList())

    private fun parse(input: Input): Pair<Rules, Updates> =
        parseRules(input.beforeBlank()) to parseUpdates(input.afterBlank())

    private fun parseRules(input: Input): Rules =
        input.map { it.split('|').map { it.toInt() }.let { it.first() to it.last() } }
            .groupBy({ it.first }, { it.second })

    private fun parseUpdates(input: Input): Updates =
        input.map { it -> it.split(',').map { it.toInt() } }

}

private typealias Updates = List<List<Int>>
private typealias Rules = Map<Int, List<Int>>
