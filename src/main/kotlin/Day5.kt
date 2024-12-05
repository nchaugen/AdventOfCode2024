import kotlin.collections.filter

object Day5 {

    fun sumOfCorrectRules(input: Input): Int =
        parse(input).let { (rules, updates) ->
            updates
                .filter { rules.isCorrect(it) }
                .sumOf(::middlePage)
        }

    fun sumOfIncorrectRules(input: Input): Int =
        parse(input).let { (rules, updates) ->
            updates
                .filterNot { rules.isCorrect(it) }
                .map { rules.correct(it) }
                .sumOf(::middlePage)
        }

    private fun middlePage(update: Pages): Int = update[update.size / 2]

    private fun Rules.isCorrect(update: Pages): Boolean =
        update.foldIndexed(true) { i, valid, page ->
            valid && update.slice(0 until i).none { before -> before in mustBeAfter(page) }
        }

    private fun Rules.correct(update: Pages): Pages =
        update.sortedWith { a, b -> if (a in mustBeAfter(b)) 1 else -1 }

    private fun Rules.mustBeAfter(page: Int): Pages =
        getOrDefault(page, emptyList())

    private fun parse(input: Input): Pair<Rules, Updates> =
        parseRules(input.beforeBlank()) to parseUpdates(input.afterBlank())

    private fun parseRules(input: Input): Rules =
        input.map { it.split('|').map { it.toInt() }.let { it.first() to it.last() } }
            .groupBy({ it.first }, { it.second })

    private fun parseUpdates(input: Input): Updates =
        input.map { it -> it.split(',').map { it.toInt() } }

}

private typealias Pages = List<Int>
private typealias Updates = List<Pages>
private typealias Rules = Map<Int, Pages>
